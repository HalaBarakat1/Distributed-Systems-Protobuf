package org.ds.library;

import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import org.ds.library.proto.Book;
import org.ds.library.proto.Library;
import org.ds.proto.Student;
import org.ds.proto.Students;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.zip.GZIPOutputStream;

/**
 * Part 3 of the practical work: build the equivalent JSON of each message and compare sizes.
 * JsonFormat produces the canonical JSON mapping of a protobuf message, so both forms hold
 * exactly the same data.
 */
public class JsonSizeComparison {

    private static final JsonFormat.Printer PRETTY = JsonFormat.printer();
    private static final JsonFormat.Printer COMPACT = JsonFormat.printer().omittingInsignificantWhitespace();

    public static void main(String[] args) throws IOException {
        courseExample();
        bookMessage();
        libraryMessage();
        growth();
    }

    /** The Students example from the course text (Mohamad, Ahmad). */
    private static void courseExample() throws IOException {
        System.out.println("===== [1] Course example: Students =====");
        Students students = Students.newBuilder()
                .addStudents(Student.newBuilder().setName("Mohamad").setAge(40))
                .addStudents(Student.newBuilder().setName("Ahmad").setAge(40))
                .build();
        printComparison(students);
    }

    private static void bookMessage() throws IOException {
        System.out.println("===== [2] One Book message =====");
        Book book = SampleData.distributedSystemsBook();
        System.out.println(PRETTY.print(book));
        printComparison(book);
    }

    private static void libraryMessage() throws IOException {
        System.out.println("===== [3] Library message (2 books) =====");
        Library library = SampleData.library();
        printComparison(library);

        // JSON -> object again, to prove both forms carry the same information
        Library.Builder fromJson = Library.newBuilder();
        JsonFormat.parser().merge(COMPACT.print(library), fromJson);
        System.out.println("Object parsed back from JSON equals original: " + fromJson.build().equals(library));

        Files.createDirectories(SampleData.SHARED_DIR);
        Files.writeString(SampleData.SHARED_DIR.resolve("library_java.json"), PRETTY.print(library));
        System.out.println();
    }

    /** How the difference grows with the number of books (plus gzip, which is common over HTTP). */
    private static void growth() throws IOException {
        System.out.println("===== [4] Size vs number of books =====");
        System.out.printf("%-7s | %12s | %12s | %10s | %7s | %10s | %10s%n",
                "books", "JSON pretty", "JSON compact", "Protobuf", "ratio", "JSON gzip", "Proto gzip");
        for (int n : new int[]{1, 10, 100, 1000}) {
            Library library = SampleData.libraryOfSize(n);
            byte[] json = COMPACT.print(library).getBytes(StandardCharsets.UTF_8);
            int pretty = PRETTY.print(library).getBytes(StandardCharsets.UTF_8).length;
            byte[] proto = library.toByteArray();
            System.out.printf("%-7d | %12d | %12d | %10d | %6.2fx | %10d | %10d%n",
                    n, pretty, json.length, proto.length, (double) json.length / proto.length,
                    gzip(json), gzip(proto));
        }
    }

    private static void printComparison(Message message) throws IOException {
        String compact = COMPACT.print(message);
        int pretty = PRETTY.print(message).getBytes(StandardCharsets.UTF_8).length;
        int json = compact.getBytes(StandardCharsets.UTF_8).length;
        int proto = message.toByteArray().length;
        if (proto <= 64) {   // print the raw content only for small messages
            System.out.println("Compact JSON : " + compact);
            System.out.println("Protobuf hex : " + Hex.format(message.toByteArray(), 1000));
        }
        System.out.printf("JSON (pretty) = %d bytes | JSON (compact) = %d bytes | Protobuf = %d bytes%n",
                pretty, json, proto);
        System.out.printf("Protobuf is %.1f%% smaller than compact JSON (JSON/Protobuf = %.2fx)%n%n",
                100.0 * (json - proto) / json, (double) json / proto);
    }

    private static int gzip(byte[] data) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (GZIPOutputStream gz = new GZIPOutputStream(bos)) {
            gz.write(data);
        }
        return bos.size();
    }
}
