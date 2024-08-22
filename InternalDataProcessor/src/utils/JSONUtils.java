//package utils;
//
//import repository.entity.UniversityEntity;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class JSONUtils {
//
//    public static void writeToCSV(String filePath, UniversityEntity student) {
//        try (FileWriter writer = new FileWriter(filePath, true)) {
//            writer.append(student.getId()).append(',')
//                    .append(student.getName()).append(',')
//                    .append(String.valueOf(student.getAge())).append(',')
//                    .append(student.getUniversity()).append(',')
//                    .append(student.getSpecialtyCode()).append(',')
//                    .append(String.valueOf(student.getDiplomaNumber())).append('\n');
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static List<UniversityEntity> readFromCSV(String filePath) {
//        List<UniversityEntity> students = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] fields = line.split(",");
//                UniversityEntity student = new UniversityEntity();
//                student.setId(fields[0]);
//                student.setName(fields[1]);
//                student.setAge(Integer.parseInt(fields[2]));
//                student.setUniversity(fields[3]);
//                student.setSpecialtyCode(fields[4]);
//                student.setDiplomaNumber(Integer.parseInt(fields[5]));
//                students.add(student);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return students;
//    }
//
//    public static void writeAllToCSV(String filePath, List<UniversityEntity> students) {
//        try (FileWriter writer = new FileWriter(filePath)) {
//            for (UniversityEntity student : students) {
//                writer.append(student.getId()).append(',')
//                        .append(student.getName()).append(',')
//                        .append(String.valueOf(student.getAge())).append(',')
//                        .append(student.getUniversity()).append(',')
//                        .append(student.getSpecialtyCode()).append(',')
//                        .append(String.valueOf(student.getDiplomaNumber())).append('\n');
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
