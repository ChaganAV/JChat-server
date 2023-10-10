package data;

import model.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileData {
    private File fileMessages = new File("messages.txt");

    private List<String> repository = new ArrayList<>();

    private File getFile() {
        return fileMessages;
    }
    // region getters
    public List<String> select() throws IOException {
        if(fileMessages.exists()) {
            Reader reader = new InputStreamReader(new FileInputStream(fileMessages));
            BufferedReader in = new BufferedReader(reader);
            String line;
            while ((line = in.readLine()) != null){
                repository.add(line);
            }

        }
        return repository;
    }

    public void insert(List<String> repository) throws IOException {
        List<String> messages = repository;
        if(fileMessages.exists()){
            FileWriter fw = new FileWriter(fileMessages,true);
            for (String msg: messages) {
                fw.write(msg+'\n');
            }
            fw.close();
        }else{
            FileWriter fw = new FileWriter(fileMessages);
        }
    }
    public void insert(String text) throws IOException {
        if(fileMessages.exists()){
            FileWriter fw = new FileWriter(fileMessages,true);
            fw.write(text+'\n');
            fw.close();
        }else{
            FileWriter fw = new FileWriter(fileMessages);
            fw.write(text+"\n");
            fw.close();
        }
    }
    private void setFile(File file) {
        this.fileMessages = file;
    }


    // endregion
}
