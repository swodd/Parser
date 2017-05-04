package com.example.dima.parser;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // коллекция обьектов типа стринг, которые парсятся с xml
        ArrayList<String> list = new ArrayList<>();
        // реализация парсера
        try {
            // находим и подключаем наш xml
            XmlPullParser parser = getResources().getXml(R.xml.contacts);
            // циклом, пока не дойдет до конца документа, разбираем xml и добавляем информацию в ArrayList
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT){
                if(parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("contact")){
                    list.add(parser.getAttributeValue(0) + " "
                        + parser.getAttributeValue(1) + "\n"
                        + parser.getAttributeValue(2));
                }
                parser.next();
            }
        } catch (Throwable t){
            // вывод короткого сообщения об ошибке
            Toast.makeText(this, "Ошибка при загрузке XML-документа: " + t.toString(), Toast.LENGTH_SHORT).show();

        }
        // отображение готовых обьектов из коллекции на экране
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
    }
}
