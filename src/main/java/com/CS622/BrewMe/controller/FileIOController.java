package com.CS622.BrewMe.controller;

import com.CS622.BrewMe.entity.Beer;
import com.CS622.BrewMe.repository.AleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Controller
public class FileIOController {

    @Autowired
    private AleRepository aleRepository;


    @GetMapping("/uploadData")
    public String uploadForm(){
        return "upload";
    }

    @PostMapping("/uploadData")
    public String uploadData(@RequestParam("file") MultipartFile file) throws IOException {

        List<Beer> beers = aleRepository.allBeers();

        File newFile = new File(System.getProperty("java.io.tmpdir")+"/"+"temp.dat");
        file.transferTo(newFile);
        FileInputStream fstream = null;

        try (ObjectInputStream infile = new ObjectInputStream(new
                FileInputStream(newFile)))
        {
            while (true)
            {
                Beer beer = (Beer) infile.readObject();
                List<Beer> matches = beers.stream().filter(b -> Objects.equals(b.getName(), beer.getName())).toList();
                if (matches.size() == 0) {
                    aleRepository.save(beer);
                }
                else {
                    System.out.println("Beer already in system");
                }

            }
        }
        catch (IOException e){
            System.out.println("End of File");
        }
        catch (Exception e) {
            System.out.println("Unexpected Error");
        }

        return "redirect:/";
    }

    @GetMapping("/exportMenu")
    public String exportMenu(){
        return "exportMenu";
    }


    @GetMapping("/exportData")
    public ResponseEntity<ByteArrayResource> exportBeers() {

        List<Beer> beers = aleRepository.allBeers();

        try (ObjectOutputStream outfile = new ObjectOutputStream(new FileOutputStream("beers.dat"))) {
            for (Beer beer : beers) {
                outfile.writeObject(beer);
            }
        } catch (Exception e) {
            System.out.println("error writing");
        }

        ByteArrayResource resource;
        File file;
        try {
            file = new File("beers.dat");
            Path path = Paths.get(file.getAbsolutePath());
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/x-java-serialized-object")).body(resource);

    }

}

