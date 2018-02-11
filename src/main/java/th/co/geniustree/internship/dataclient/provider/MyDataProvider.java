package th.co.geniustree.internship.dataclient.provider;

import th.co.geniustree.internship.api.dataproviderapi.DataProviderApi;
import th.co.geniustree.internship.dataclient.xx.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MyDataProvider implements DataProviderApi {
    @Override
    public List<String> dataProvider() {
        return Arrays.asList("1","2","3");
    }
}
