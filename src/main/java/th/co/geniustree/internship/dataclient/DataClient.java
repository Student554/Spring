package th.co.geniustree.internship.dataclient;

import th.co.geniustree.internship.dataclient.provider.MyDataProvider;
import th.co.geniustree.internship.dataclient.xx.AutoWired;
import th.co.geniustree.internship.dataclient.xx.Service;

@Service
public class DataClient {
    @AutoWired
    private MyDataProvider dataProviderApi;

    public void test() {
    ///conn.open
            System.out.println(dataProviderApi.dataProvider());
//con.close
    }

    @Override
    public String toString() {
        return "DataClient{" +
                "dataProviderApi=" + dataProviderApi +
                '}';
    }
}
