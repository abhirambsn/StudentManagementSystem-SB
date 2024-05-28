package com.abhirambsn.studentmanagementsystem.mappers;

import com.abhirambsn.studentmanagementsystem.dto.CourseDto;
import com.abhirambsn.studentmanagementsystem.models.Course;
import com.abhirambsn.studentmanagementsystem.util.IdGenerator;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {
    public Course toCourse(CourseDto courseDto) {
        Course course = new Course();
        String course_code = IdGenerator.generateCourseCode(
                courseDto.department_id(),
                courseDto.program_id(),
                courseDto.year_of_start()
        );

        course.setCourse_code(course_code);
        course.setCourse_name(courseDto.course_name());
        course.setCourse_description_link(courseDto.description_link());
        course.setCourse_type(courseDto.course_type());
        course.setCourse_assignment_type(courseDto.course_assignment_type());
        course.setYear_of_start(courseDto.year_of_start());


        return course;
    }
}
