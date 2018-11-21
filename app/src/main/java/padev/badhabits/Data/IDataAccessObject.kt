package padev.badhabits.Data;

import java.util.ArrayList;

public interface IDataAccessObject {

    fun insertData(data: AbstractData)

    fun selectData(id: Int): AbstractData

    fun selectAllData(): ArrayList<AbstractData>

    fun updateData(data: AbstractData): Int

    fun deleteData(id: Int)

    fun deleteAllData()
}