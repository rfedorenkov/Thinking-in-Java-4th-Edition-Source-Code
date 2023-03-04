package exercises.chapter18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * Exercise 3
 * Modify DirList.java (or one of its variants) so that it sums
 * up the file sizes of the selected files.
 * {Args: "E03.*\.java"}
 */
public class E03_DirSize {
    public static FilenameFilter filter(final String regex) {
        // Creation of anonymous inner class:
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        }; // End of anonymous inner class
    }

    public static void main(String[] args) {
        File path = new File("./src/exercises/chapter18");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
            list = path.list(filter(args[0]));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        long totalSize = 0;
        long size;
        print("List files:");
        for (String dirItem : list) {
            size = new File(path, dirItem).length();
            print(dirItem + ", " + size + " byte(s)");
            totalSize += size;
        }
        print("------------------------------");
        print("Total: " + list.length + " file(s), " + totalSize + " bytes");
    }
}
/* Output:
List files:
E01_SearchWord.java, 2433 byte(s)
E02_SortedDirListTest.java, 1534 byte(s)
E03_DirSize.java, 1521 byte(s)
E04_DirSize2.java, 1037 byte(s)
E05_ProcessFiles2.java, 1790 byte(s)
E06_ProcessFiles3.java, 1177 byte(s)
E07_ReverseReadLines.java, 1243 byte(s)
E08_ReadFileFromCommandLine.java, 814 byte(s)
E09_UpperCase.java, 765 byte(s)
E10_FindWords.java, 950 byte(s)
E11_GreenhouseConfig.txt, 116 byte(s)
E11_GreenhouseController2.java, 4794 byte(s)
E12_CountLines.java, 1091 byte(s)
E13_LineNumber.java, 936 byte(s)
E14_BufferPerformance.java, 1296 byte(s)
E15_StoringAndRecoveringData2.java, 2275 byte(s)
E16_UsingRandomAccessFile2.java, 2704 byte(s)
E17_CharactersInfo.java, 1355 byte(s)
E18_TextFile2.java, 2555 byte(s)
E19_BytesInfo.java, 1336 byte(s)
E20_ClassBeginChecker.java, 1120 byte(s)
E21_Echo2.java, 817 byte(s)
E22_OSExecuteDemo2.java, 2190 byte(s)
E23_PrintCharBuffer.java, 1205 byte(s)
E24_DoubleBufferDemo.java, 852 byte(s)
E25_AllocateDirectTest.java, 10733 byte(s)
E26_JGrepMemoryMapped.java, 1684 byte(s)
E27_ObjectsSerialization.java, 1820 byte(s)
E28_BlipCheck.java, 2471 byte(s)
E29_Blip3Test.java, 2086 byte(s)
E30_RepairCADState.java, 5610 byte(s)
E31_PersonWithAddress.java, 3698 byte(s)
E32_WordsCountXML.java, 1812 byte(s)
E33_PreferencesDemo2.java, 1129 byte(s)
------------------------------
Total: 34 file(s), 68949 bytes
 */