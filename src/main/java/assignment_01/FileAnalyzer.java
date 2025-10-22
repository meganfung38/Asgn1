package assignment_01;
import java.io.*;
import java.util.*;


// component: calculates complexity, size, and overall


public class FileAnalyzer {

    public int complexityMetric(File file) throws IOException {

        int lineCount = 0;  // keep track of lines

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {  // try to create a file reader
            while (reader.readLine() != null) {  // read lines until EOF
                lineCount++;  // increment lines
            }
        }

        return lineCount;

    }

    public int sizeMetric(File file) throws IOException {

        List<String> controlStatements = Arrays.asList("if", "for", "while", "switch");  // list of control statements

        int controlStatementCount = 0;  // keep track of control statements

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {  // try to create a file reader
            String curr;  // current line being read

            while ((curr = reader.readLine()) != null) {  // read lines until EOF
                for (String controlStatement : controlStatements) {  // iterate through control statements
                    if (curr.contains(controlStatement)) {  // current line contains a control statement
                        controlStatementCount++;  // increment control statements
                    }
                }
            }
        }

        return controlStatementCount;

    }

    public int overallMetric(File file) throws IOException {

        boolean author = false;  // @author is in file
        boolean version = false;  // @version is in file

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {  // try to create a file reader
            String curr;  // current line being read

            while ((curr = reader.readLine()) != null) {  // read lines until EOF
                curr = curr.trim();  // clean

                if (curr.contains("@author")) {  // @author is in file
                    author = true;
                }
                if (curr.contains("@version")) {  // @version is in file
                    version = true;
                }
            }
        }

        if (author && version) {  // happy
            return 1;
        } else if (author || version) {  // neutral
            return 0;
        } else {  // sad
            return -1;
        }

    }

}
