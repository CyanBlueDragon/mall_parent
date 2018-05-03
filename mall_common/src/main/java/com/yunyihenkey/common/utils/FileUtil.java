package com.yunyihenkey.common.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	public static void writeFile(String path, String content) throws Exception {
		File file = new File(path);
		if (!(file.isFile())) {
			file.createNewFile();
		}

		FileWriter writer = null;
		try {
			writer = new FileWriter(path, true);
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}