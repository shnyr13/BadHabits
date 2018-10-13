package padev.badhabits.Data;

import java.util.ArrayList;

public interface IDataAccessObject {

    void insertData(AbstractData data);

    AbstractData selectData(int id);

    ArrayList<AbstractData> selectAllData();

    int updateData(AbstractData data);

    void deleteData(int id);

    void deleteAllData();
}