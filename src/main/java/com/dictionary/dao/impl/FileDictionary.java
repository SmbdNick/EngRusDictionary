package com.dictionary.dao.impl;

import com.dictionary.dao.api.Dictionary;
import com.dictionary.dto.CreateWord;
import com.dictionary.dto.GetWord;
import com.dictionary.model.Word;
import com.dictionary.ui.impl.console.commands.ConsoleInteractions;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class FileDictionary implements Dictionary {
    private static String fileDir;
    private final File dictionaryFile;
    private String name;
    ConsoleInteractions consoleInteractions = new ConsoleInteractions();

    public static String getFileDir() {
        return fileDir;
    }

    public FileDictionary(String name) {
        this.name = name;
        StringBuilder pathBuilder = new StringBuilder(this.getClass()
                .getClassLoader()
                .getResource(File.separator)
                .getPath());
        File tempPath = new File(pathBuilder.append(File.separator)
                .append("temp")
                .toString());
        fileDir = String.valueOf(tempPath);
        if (!tempPath.exists()) {
            tempPath.mkdirs();
        }
        dictionaryFile = new File(pathBuilder.append(File.separator)
                .append(File.separator)
                .append(name)
                .append(".txt")
                .toString());
        if (!dictionaryFile.exists()) {
            try {
                dictionaryFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            consoleInteractions.say("This dictionary exists! Edit it!");
        }
    }

    public void deleteFile() {
        dictionaryFile.delete();
    }

    @Override
    public void modify(final CreateWord editWord) {
        List<String> values = new ArrayList<>(editWord.getValues());

        try {
            deleteWord(editWord.getKey());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addWord(new CreateWord(editWord.getKey(), values));
    }

    @Override
    public Optional<GetWord> get(final String key) {
        Scanner scanner;
        List<String> currentLine;

        try {
            scanner = new Scanner(dictionaryFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNextLine()) {
            currentLine = fileLineParser(scanner.nextLine());
            if (currentLine.get(0).equals(key)) {
                return Optional.of(new GetWord(currentLine.subList(1, currentLine.size())));
            }
        }

        return Optional.empty();
    }

    @Override
    public Set<Word> getAllWords() {
        return readAllFile();
    }

    @Override
    public void deleteWord(final String key) throws IOException {
        File inputFile = dictionaryFile;
        File tempFile = new File(fileDir + "_tempfile_.txt");

        BufferedReader reader;

        reader = new BufferedReader(new FileReader(inputFile));

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        while (true) {
            if ((currentLine = reader.readLine()) == null)
                break;

            List<String> trimmedLine = fileLineParser(currentLine);
            if (trimmedLine.get(0).equals(key))
                continue;

            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        Files.delete(inputFile.toPath());
        tempFile.renameTo(inputFile);
    }

    private Set<Word> readAllFile() {
        Scanner scanner;
        Set<Word> words = new HashSet<>();

        try {
            scanner = new Scanner(dictionaryFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNextLine()) {
            List<String> parsedLine = new ArrayList<>(fileLineParser(scanner.nextLine()));
            Word word = new Word(parsedLine.get(0), parsedLine.subList(1, parsedLine.size()));
            words.add(word);
        }
        scanner.close();
        return words;
    }

    private List<String> fileLineParser(String wordLine) {
        List<String> result = new ArrayList<>();

        String[] keyPlusValues = wordLine.split("-");
        result.add(keyPlusValues[0]);
        String[] values = keyPlusValues[1].split(",");
        for (String value : values) {
            result.add(value);
        }

        return result;
    }

    private void fileWriter(String addedWord, Boolean rewriteFile) {
        FileWriter fileWriter;

        try {
            fileWriter = new FileWriter(dictionaryFile, !rewriteFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            fileWriter.write(addedWord);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addWord(CreateWord word) {
        String addedWord = word.getKey() + "-" + String.join(",", word.getValues()) + "\n";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dictionaryFile, true))) {
            bw.write(addedWord);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isWordExist(String key) {
        Set<Word> words = readAllFile();
        for (Word word : words) {
            if (word.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
