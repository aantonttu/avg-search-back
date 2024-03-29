package c_theory.question14.lessons;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("lessons")
@RestController
public class LessonsController {


    //todo for question 14 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo
    // You are creating a rest controller for lessons. Think page where you are looking at lessons like echo360.
    // You need to add necessary annotations and methods to this class.
    // This class should compile.
    // It should run successfully when moved to your application package.
    // Method body is not important and will not be graded.
    // Modifying other classes is unnecessary and will not be graded.

    //todo A add necessary annotations on the class +

    //todo B create a method to query lessons (plural)
    @GetMapping
    public List<Lesson> getLessons(@RequestParam(value = "course", required = false) Long course,
                                  @RequestParam(value = "year", required = false, defaultValue = "2020") Long year,
                                   @RequestParam(value = "order", defaultValue = "predefined") String order) {
        //order = asc/desc
        return new ArrayList<>();
    }

    //todo C create a method to query single lesson
    @GetMapping("{name}")
    public Lesson getLesson(@PathVariable String name) {
        return new Lesson();
    }


    //todo D create a method to save a lesson
    @PostMapping()
    public Lesson saveLesson(@RequestBody Lesson lesson) {
        return lesson;
    }

    //todo E create a method to update a lesson
    @PutMapping("{name}")
    public Lesson updateLesson(@PathVariable String name, @RequestBody Lesson lesson) {
        return lesson;
    }

    //todo F create a method to delete a lesson
    @DeleteMapping("{name}")
    public void deleteLesson(@PathVariable String name) {
    }

    //todo G assuming each Lesson has students (one-to-many relation) create a method to query lesson's students
    @GetMapping("{name}/students")
    public List<Students> getStudents(@PathVariable String name) {
        return new ArrayList<>();
    }

    //todo H create a method to update lesson's name (and nothing else)
    @PutMapping("{name1}/to/{name2}")
    public Lesson updateLesson(@PathVariable String name1, @PathVariable String name2) {
        return new Lesson();
    }

    //todo G modify correct method to support searching lessons by course id while keeping original functionality +

    //todo H modify correct method to support searching by year with default being current year (2020)
    // (you can ignore semesters or use year-semester string) +

    //todo K modify correct method to order lessons
    // * by most visitors first
    // * by least visitors first
    // (you can assume that by default it searches by predefined lecturer's order) +

}
