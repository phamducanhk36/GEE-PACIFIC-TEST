package test2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {

    public static final Path COMMAND_FILE_PATH = Path.of(System.getProperty("user.dir") + "\\src\\test2\\commands.txt");

    public static void main(String[] args) {
        try {
            String commands = Files.readString(COMMAND_FILE_PATH);
            List<String> commandLines = commands.lines().filter(line -> !line.isBlank())
                    .map(String::strip)
                    .collect(Collectors.toList());
            List<Chessman> chessmen = commandLines.stream().map(Utils::initChess).collect(Collectors.toList());
            while (!Utils.isDone(chessmen)) {
                chessmen.forEach(c -> {
                    c.doStep();
                    if (Utils.isOverlap(c, chessmen)) {
                        c.back();
                    }
                });
            }
            System.out.println("Result: " + chessmen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
