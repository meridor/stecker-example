package ru.meridor.tools.plugin;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class Hello {

    public static void main(String[] args) throws Exception {

        Path currentDirectory = Paths.get(System.getProperty("user.dir"), "plugins");

        System.out.println("Searching for plugins in " + currentDirectory + "...");

        try {
            PluginRegistry pluginRegistry = PluginLoader
                    .withPluginDirectory(currentDirectory)
                    .withExtensionPoints(Greeter.class)
                    .load();

            List<String> plugins = pluginRegistry.getPluginNames();
            System.out.println("Found " + plugins.size() + " plugins:");
            for (String pluginName: plugins){
                System.out.println("\t" + pluginName);
            }

            List<Class> greeterClasses = pluginRegistry.getImplementations(Greeter.class);
            System.out.println("Found " + greeterClasses.size() + " Greeter implementations:");

            for (Class greeterClass: greeterClasses) {
                System.out.println("Greeting with " + greeterClass.getCanonicalName() + ":");
                Greeter greeter = (Greeter) greeterClass.newInstance();
                greeter.greet(args);
            }
        } catch (PluginException e) {
            handlePluginException(e);
        }

    }

    /**
     * This is how you can get information about plugin issues encountered
     * @param e plugin exception caught
     */
    private static void handlePluginException(PluginException e) {
        System.out.println("Caught plugin exception:");
        e.printStackTrace();
        Optional<PluginMetadata> plugin = e.getPluginMetadata();
        if (plugin.isPresent()) {
            System.out.println("Affected plugin: " + plugin.get().getName());
        }
        Optional<DependencyProblem> dependencyProblem = e.getDependencyProblem();
        if (dependencyProblem.isPresent()) {
            DependencyProblem dp = dependencyProblem.get();
            System.out.println("Dependency problem discovered:");
            System.out.println("\tMissing dependencies:");
            for (Dependency dependency: dp.getMissingDependencies()) {
                System.out.println("\t\t" + dependency.getName() + ":" + dependency.getVersion());
            }
            System.out.println("\tConflicting dependencies:");
            for (Dependency dependency: dp.getConflictingDependencies()) {
                System.out.println("\t\t" + dependency.getName() + ":" + dependency.getVersion());
            }
        }
    }

}
