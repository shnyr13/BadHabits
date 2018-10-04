package padev.badhabits.Data;

import java.util.ArrayList;

public interface IDataAccessObject {

    void insertData(IData data);

    IData selectData(int id);

    ArrayList<IData> selectAllData();

    int updateData(IData data);

    void deleteData(int id);

    void deleteAllData();
}
