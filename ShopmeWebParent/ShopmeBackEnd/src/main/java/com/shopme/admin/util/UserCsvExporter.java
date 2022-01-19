package com.shopme.admin.util;

import com.shopme.common.entity.User;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserCsvExporter extends AbstractExporter {
    public void export(List<User> userList, HttpServletResponse httpServletResponse) throws IOException {

        super.SetResponseHeader(httpServletResponse, "text/csv", ".csv");

        ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(httpServletResponse.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"User ID", "E-mail", "First Name", "Last Name", "Roles", "Enabled"};
        String[] csvFieldMapping = {"id", "email", "firstName", "lastName", "roles", "enabled"};

        csvBeanWriter.writeHeader(csvHeader);
        userList.forEach(user -> {
            try {
                csvBeanWriter.write(user, csvFieldMapping);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        csvBeanWriter.close();
    }
}
