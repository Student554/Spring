package th.co.geniustree.internship.dataclient;

import th.co.geniustree.internship.dataclient.provider.MyDataProvider;
import th.co.geniustree.internship.dataclient.provider.MyDataProvider1;
import th.co.geniustree.internship.dataclient.xx.AutoWired;
import th.co.geniustree.internship.dataclient.xx.Service;

@Service
public class DataClient {
    @AutoWired
    private MyDataProvider dataProviderApi;

    public void test() {

        System.out.println(dataProviderApi.dataProvider());
        System.out.println(dataProviderApi1.dataProvider());

    }

    @AutoWired
    private MyDataProvider1 dataProviderApi1;

    public void test1() {

        System.out.println(dataProviderApi1.dataProvider());

    }

    @Override
    public String toString() {
        return "DataClient{" +
                "dataProviderApi=" + dataProviderApi +
                '}';
    }
}
