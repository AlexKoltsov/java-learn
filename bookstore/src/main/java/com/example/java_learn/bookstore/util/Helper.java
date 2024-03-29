package com.example.java_learn.bookstore.util;

import com.example.java_learn.bookstore.exception.UnknownCreatorException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Helper {

    public static Date strToDate(String date) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy").parse(date);
    }

    public static <T> List<T> loadFromFile(String fileName, FunctionThrowable<String, T, ParseException> strToObj) throws IOException {
        return Files.lines(Paths.get(fileName))
                .map(line -> {
                    try {
                        return Optional.of(strToObj.apply(line));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return Optional.<T>empty();
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public static <T, U> List<T> loadFromFileWithDependency(String fileName,
                                                            BiFunctionThrowable<String, U, T, ParseException> strToObj,
                                                            U var2) throws IOException {
        return Files.lines(Paths.get(fileName))
                .map(line -> {
                    try {
                        return Optional.of(strToObj.apply(line, var2));
                    } catch (ParseException | UnknownCreatorException e) {
                        e.printStackTrace();
                        return Optional.<T>empty();
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
