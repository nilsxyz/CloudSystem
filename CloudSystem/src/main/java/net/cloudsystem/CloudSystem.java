package net.cloudsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.cloudsystem.abstractes.Command;
import net.cloudsystem.commands.End;
import net.cloudsystem.commands.ProcessList;
import net.cloudsystem.managers.CloudManager;
import net.skillcloud.misc.Server;

/*
 * @author JoniP2804
 * @Copyright by JoniP2804, 24.08.2017, 14:44:29
 * @Created on 24.08.2017, 14:44:29
 */
public class CloudSystem {

    public static List<Command> list = new ArrayList<>();
    public static Map<String, Server> processes = new HashMap<>();

    private static CloudManager cloudManager;

    public static void main(String[] args) {

        cloudManager = new CloudManager();

        System.out.println("");
        System.out.println("  _____ _                 _  _____           _                 ");
        System.out.println(" / ____| |               | |/ ____|         | |                ");
        System.out.println("| |    | | ___  _   _  __| | (___  _   _ ___| |_ ___ _ __ ___  ");
        System.out.println("| |    | |/ _ \\| | | |/ _` |\\___ \\| | | / __| __/ _ \\ '_ ` _ \\ ");
        System.out.println("| |____| | (_) | |_| | (_| |____) | |_| \\__ \\ ||  __/ | | | | |");
        System.out.println(" \\_____|_|\\___/ \\__,_|\\__,_|_____/ \\__, |___/\\__\\___|_| |_| |_| by JoniP2804 | mrleichtlp");
        System.out.println("                                    __/ |                      ");
        System.out.println("                                   |___/                       ");
        System.out.println("");
        System.out.println("");

        cloudManager.startLobbys();

        cloudManager.createConfig();
        cloudManager.createFiles();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        new ProcessList();
        new End();

        while (true) {

            try {
                System.out.print("> ");
                String input = br.readLine();
                try {
                    list.stream().forEach(commands -> {
                        commands.execute(input.split(" "));
                    });
                } catch (Exception e) {
                }
            } catch (IOException ex) {
                Logger.getLogger(CloudSystem.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
