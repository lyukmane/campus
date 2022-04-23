package person;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonToJava {
    public static void main (String[] args) throws IOException {
        Person person = new Person("Nyk", "Rivne", 30, Arrays.asList("dir1", "dir2", "dir3"));
        try(Reader reader = new InputStreamReader(JsonToJava.class.getResourceAsStream("/server.json"), StandardCharsets.UTF_8)){

            Gson gson = new GsonBuilder().create();
            String generetedJson = gson.toJson(person);
            System.out.println(generetedJson);

            Person p = gson.fromJson(reader, Person.class);
            System.out.println(p);
            System.out.println(p.getAge());

            String s = gson.toJson(p);
            System.out.println(s);

        }

    }
}
