package c_theory.question14.chairs;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;

@RequestMapping("chairs")
@RestController
public class ChairsController {

    //todo for question 14 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo
    // You are creating a rest controller for chairs.
    // Think a backoffice system for furniture shop like Aatrium or some Kalamaja chair boutique.
    // You need to add necessary annotations and methods to this class.
    // This class should compile.
    // It should run successfully when moved to your application package.
    // Method body is not important and will not be graded.
    // Modifying other classes is unnecessary and will not be graded.

    //todo A add necessary annotations on the class DONE

    //todo B create a method to query chairs (plural)
    @GetMapping
    public List<Chair> getChairs(@RequestParam(value = "type", required = false) String type,
                                 @RequestParam(value = "available", defaultValue = "true", required = false) boolean status,
                                 @RequestParam(value = "by", defaultValue = "popular") String by,
                                 @RequestParam(value = "order", defaultValue = "desc") String order) {
        // chairs?type=soft
        // chairs?available=true
        // chairs?by=popular&order=desc
        // chairs?by=price&order=desc
        // chairs?by=price&order=asc
        // chairs
        return new ArrayList<>();
    }

    //todo C create a method to query single chair
    @GetMapping("{name}")
    public Chair getChair(@PathVariable String name) {
        return new Chair();
    }

    //todo D create a method to save a chair
    @PostMapping()
    public Chair saveChair(@RequestBody Chair chair) {
        return chair;
    }

    //todo E create a method to update a chair
    @PutMapping("{name}")
    public Chair updateChair(@RequestBody Chair chair, @PathVariable String name) {
        return chair;
    }


    //todo F create a method to delete a chair
    @DeleteMapping("{name}")
    public void deleteChair(@PathVariable String name) {

    }

    //todo G assuming each chair has a designer (one-to-one relation) create a method to query chair's designer
    @GetMapping("{name}/designer")
    public Designer getChairDesigner(@PathVariable String name) {
        return new Designer();
    }

    //todo H create a method to update chair's name (and nothing else)
    @PutMapping("{oldName}/to/{newName}")
    public Chair updateChairName(@PathVariable String oldName, @PathVariable String newName) {
        return new Chair();
    }

    //todo I modify correct method to support searching chairs by type while keeping original functionality

    // getChairs()

    //todo J modify correct method to support searching chairs by whether chair is in stock while keeping original functionality

    // getChairs()

    //todo K modify correct method to order/sort chairs
    // * by lowest priced first
    // * by highest priced first
    // (you can assume that by default it searches most popular first)

    // getChairs()

}
