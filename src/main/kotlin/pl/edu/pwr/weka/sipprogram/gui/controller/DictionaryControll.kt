package pl.edu.pwr.weka.sipprogram.gui.controller

import com.opencsv.bean.ColumnPositionMappingStrategy
import com.opencsv.bean.CsvToBeanBuilder
import pl.edu.pwr.weka.sipprogram.util.MessageBundle
import tornadofx.*
import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStreamReader

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 09.12.2018 13:17
 */
class DictionaryController : Controller() {
    val model = DictionaryItemModel()
    val dictionaryList = mutableListOf<DictionaryItem>().observable()

    init {

        messages = MessageBundle("Dictionary")
        initDictionary()
        showSelectedItem(dictionaryList[0])
    }

    private fun initDictionary() {
        val bufferedReader = BufferedReader(InputStreamReader(javaClass.getResourceAsStream("/Dictionary.csv"),"UTF-8"))
        val mappingStrategy = ColumnPositionMappingStrategy<DictionaryItem>()
        mappingStrategy.type = DictionaryItem::class.java
        mappingStrategy.setColumnMapping("itemType","name","urlName","url","description")
        val csvToBean = CsvToBeanBuilder<DictionaryItem>(bufferedReader)
            .withMappingStrategy(mappingStrategy)
            .withIgnoreQuotations(true)
            .withSeparator(';')
            .withSkipLines(1)
            .build()

        val tmpDictionaryList = csvToBean.parse()
        dictionaryList.addAll(tmpDictionaryList)
    }

    fun showSelectedItem(it: DictionaryItem) {
        model.name.value = it.name
        model.description.value = it.description.replace("\\n","\n")
        model.urlName.value = it.urlName
        model.url.value = it.url
    }
}