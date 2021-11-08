package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> waySave = new ArrayList<String>();
        File dir = new File("E://Games//savegames");
        waySave.add("E://Games//savegames//save1.dat");
        waySave.add("E://Games//savegames//save2.dat");
        waySave.add("E://Games//savegames//save3.dat");
        String wayZip = "E://Games//savegames//zip.zip";
        GameProgress gameProgress1 = new GameProgress(60, 3, 20, 8.6);
        GameProgress gameProgress2 = new GameProgress(25, 5, 80, 35.3);
        GameProgress gameProgress3 = new GameProgress(52, 2, 35, 12.4);
        saveGame(waySave.get(0),gameProgress1);
        saveGame(waySave.get(1),gameProgress2);
        saveGame(waySave.get(2),gameProgress3);
        zipFiles(wayZip, waySave);
        clearFiles(dir);


    }

    public static void saveGame(String way, GameProgress gameProgress){
        try (FileOutputStream fos = new FileOutputStream(way);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(gameProgress);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String wayZip, ArrayList<String> waySave ) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(wayZip));) {
            for (int i = 0; i < waySave.size(); i++) {
                FileInputStream fis = new FileInputStream(waySave.get(i));
                ZipEntry entry = new ZipEntry("packed_save" + i + ".dat");
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                zout.write(buffer);
                fis.read(buffer);
                zout.closeEntry();
                fis.close();
//                Files.delete(Path.of(waySave.get(i)));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void clearFiles(File dir) {
        if (dir.isDirectory()) {
            for (File item : Objects.requireNonNull(dir.listFiles())) {
                if (!item.getName().equals("zip.zip")) {
                    item.delete();
                }
            }
        }
    }

}
