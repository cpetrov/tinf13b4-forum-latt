package tinf13b4.forum.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;


public class Base64Decryptor {

	/*
	 * Position 0: Hostname
	 * Position 1: Port
	 * Position 2: Databasename
	 * Position 3: Username
	 * Position 4: Password
	 */
	public List<String> getDecryptedDataList(final String filePath) {
		List<String> dataList = getDataList(filePath);
		decryptDataList(dataList);
		return dataList;
	}

	private void decryptDataList(List<String> dataList) {
		for (int i = 0; i < dataList.size(); i++) {
			dataList.set(i, decrypt(dataList.get(i)));
		}
	}

	private List<String> getDataList(final String filePath) {
		List<String> data = new ArrayList<>();
		try {
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			data = new ArrayList<>();
			while (reader.ready()) {
				data.add(reader.readLine());
			}
			reader.close();
		} catch (Exception e) {
			// TODO deal with the exception
			e.printStackTrace();
		}
		return data;
	}

	public String decrypt(String s) {
		return new String(DatatypeConverter.parseBase64Binary(s));
	}
}
