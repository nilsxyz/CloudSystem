package net.cloudsystem.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.cloudsystem.CloudSystem;
import net.cloudsystem.misc.PacketSender;
import net.skillcloud.misc.Server;
import org.apache.commons.io.FileUtils;

/*
 * @author JoniP2804
 * @Copyright by JoniP2804, 24.08.2017, 15:47:20
 * @Created on 24.08.2017, 15:47:20
 */
public class CloudManager {

    private final File props = new File("config.properties");
    private final Properties properties = new Properties();

    private PacketSender sender;

    //<editor-fold defaultstate="collapsed" desc="create">
    public void createConfig() {
        try {
            if (!props.exists()) {
                props.createNewFile();
            }
        } catch (IOException ex) {
            Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        setProperty("lobbys", "5");
        try {
            OutputStream outputStream = new FileOutputStream(props);
            properties.store(outputStream, "");
        } catch (IOException ex) {
            Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createFiles() {
        try {
            properties.load(new FileInputStream(props));
        } catch (IOException ex) {
            Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 1; i != (Integer.valueOf(properties.getProperty("lobbys")) + 1); i++) {
            File file = new File("tmp/lobbys/Lobbys/Lobby-" + i);
            file.mkdirs();
        }
        File file = new File("tmp/lobbys/plugins");
        file.mkdirs();

        file = new File("tmp/lobbys/world");
        file.mkdirs();

        file = new File("tmp/proxy");
        file.mkdirs();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setProperty">
    private void setProperty(String name, String value) {
        try {
            properties.load(new FileInputStream(props));
        } catch (IOException ex) {
            Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (isNull(name)) {
            properties.setProperty(name, value);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="isNull">
    private boolean isNull(String name) {
        try {
            properties.load(new FileInputStream(props));
        } catch (IOException ex) {
            Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (properties.getProperty(name) == null) {
            return true;
        } else {
            return false;
        }
    }
    //</editor-fold>

    public void startLobbys() {
        File file = new File("tmp/lobbys");
        if (file.exists()) {
            try {
                properties.load(new FileInputStream(props));
            } catch (IOException ex) {
                Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            int integers = Integer.parseInt(properties.getProperty("lobbys"));

            File[] files = file.listFiles();

            for (int i = 0; i != files.length; i++) {
                File file1 = files[i];
                if (!file1.getName().equalsIgnoreCase("Lobbys")) {
                    for (int i2 = 1; i2 != (integers + 1); i2++) {
                        try {
                            if (!file1.isDirectory()) {
                                FileUtils.copyFileToDirectory(file1, new File("tmp/lobbys/Lobbys/Lobby-" + i2));
                            } else {
                                FileUtils.copyDirectoryToDirectory(file1, new File("tmp/lobbys/Lobbys/Lobby-" + i2));
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

            try {
                try {
                    for (int i = 1; i != (integers + 1); i++) {
                        ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
                        String[] array = {"java", "-Xms512M", "-Xmx1024M", "-jar", "spigot.jar", "-p", i + "0000"};
                        processBuilder.command(array);
                        processBuilder.directory(new File("tmp/lobbys/Lobbys/Lobby-" + i));
                        Process process = processBuilder.start();
                        System.out.println("Die Lobby-" + i + " wurde gestartet");
                        CloudSystem.processes.put("Lobby-" + i, new Server(UUID.randomUUID().toString(), i + "0000", process, "Lobby-" + i));
                    }
                } catch (NumberFormatException ex) {
                    Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
