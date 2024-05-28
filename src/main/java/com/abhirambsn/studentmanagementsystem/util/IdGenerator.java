package com.abhirambsn.studentmanagementsystem.util;

import java.util.Map;


public class IdGenerator {
    private static final Map<String, String> branch_id_map = Map.of(
        "BT", "1",
        "ECE", "2",
        "CSE", "3"
    );

    private static final Map<String, String> program_id_map = Map.of(
    "BT", "10",
    "INTGT", "80",
    "MT", "40"
    );

    private static final Map<String, String> department_code_course_map = Map.of(
        "CSE", "CI",
        "HSS", "HS",
        "MATHS", "MA",
        "PHYSICS", "PH",
        "BIOTECH", "BT"
    );

    private static final Map<String, String> program_id_course_map = Map.of(
        "BT", "B",
        "MT", "M"
    );



    public static String generateStudentEnrolmentNumber(String branch_id, String program_id, int year_of_joining) {
        // Get Last 2 Digits of year of joining
        String year_of_joining_last_2_digits = Integer.toString(year_of_joining).substring(2);
        // Get Branch ID
        String branch_id_2_digits = branch_id_map.get(branch_id);
        // Get Program ID
        String program_id_2_digits = program_id_map.get(program_id);

        // Generate Random Number between 1 and 546 and add 0 if it is less than 100
        String student_id = String.valueOf((int) (Math.random() * 546));
        if (student_id.length() == 1) {
            student_id = "00" + student_id;
        } else if (student_id.length() == 2) {
            student_id = "0" + student_id;
        }
        // Generate and Return Enrolment Number
        return year_of_joining_last_2_digits + program_id_2_digits + branch_id_2_digits + student_id;
    }

    public static String generateFacultyId(String department_code) {
        // Generate 4 Random Digits
        String faculty_id = String.valueOf((int) (Math.random() * 10000));

        return department_code + faculty_id;
    }

    public static String generateCourseCode(String department_code, String program_id, int year_of_start) {
        // Get Department Code
        String department_code_2_digits = department_code_course_map.get(department_code);
        // Get Program ID
        String program_id_1_digit = program_id_course_map.get(program_id);
        // Get Last 2 Digits of year of start
        String year_of_start_last_2_digits = Integer.toString(year_of_start).substring(2);
        // Generate 3 Random Digits
        String course_id = String.valueOf((int) (Math.random() * 1000));

        return year_of_start_last_2_digits + program_id_1_digit + department_code_2_digits + course_id;
    }

    public static String generateUsernameFaculty(String first_name, String last_name) {
        return first_name.toLowerCase() + "." + last_name.toLowerCase();
    }

    public static String generateRandomPassword(String first_name, String last_name) {
        // Generate 5 Random Digits
        String random_password = String.valueOf((int) (Math.random() * 100000));

        return first_name.toLowerCase() + last_name.toLowerCase() + "_" + random_password;
    }
}
