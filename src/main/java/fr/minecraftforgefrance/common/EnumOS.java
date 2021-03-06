package fr.minecraftforgefrance.common;

import java.io.File;
import java.util.Locale;

public enum EnumOS
{
    WINDOWS,
    MACOS,
    UNIX,
    UNKNOWN;

    public static EnumOS getPlatform()
    {
        String osName = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        if(osName.contains("win"))
        {
            return WINDOWS;
        }
        if(osName.contains("mac"))
        {
            return MACOS;
        }
        if(osName.contains("solaris") || osName.contains("sunos") || osName.contains("linux") || osName.contains("unix"))
        {
            return UNIX;
        }
        return UNKNOWN;
    }

    public static File getMinecraftDefaultDir()
    {
        String userHome = System.getProperty("user.home", ".");
        switch(getPlatform())
        {
            case UNIX:
                return new File(userHome, ".minecraft");
            case WINDOWS:
                String applicationData = System.getenv("APPDATA");
                String folder = applicationData != null ? applicationData : userHome;
                return new File(folder, ".minecraft");
            case MACOS:
                return new File(new File(new File(userHome, "Library"), "Application Support"), "minecraft");
            default:
                return new File(userHome, "minecraft");
        }
    }

    public static String getJavaExecutable()
    {
        String separator = System.getProperty("file.separator");
        String path = System.getProperty("java.home") + separator + "bin" + separator;
        if((getPlatform() == WINDOWS) && (new File(path + "javaw.exe").isFile()))
        {
            return path + "javaw.exe";
        }
        return path + "java";
    }
}