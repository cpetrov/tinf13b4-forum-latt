
package tinf13b4.forum.util;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	public static List<String> getStringListFromInputStream(InputStream inputStream) {
		checkArgument(inputStream != null, "InputStream must not be null.");
		List<String> data = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			data = new ArrayList<>();
			while (bufferedReader.ready()) {
				data.add(bufferedReader.readLine());
			}
			bufferedReader.close();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return data;
	}
}
