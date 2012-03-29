package com.music.desk.manager;

import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.badlogic.gdx.utils.XmlWriter;
import com.music.desk.Config;
import com.music.desk.proxy.IDataProxy;

public class XMLMgr implements IXMLMgr {
	/*
	 * <root title="song1">
  		<note time="1344" type="A" duration="125"/>
  		</root>
  		<song id='0' title='song1' note='../data/1.xml' file='../music/1.mp3'/>
	 */
	private static XMLMgr instance=null;
	private XmlReader reader;
	private XmlWriter writer;
	private Element root;
	private String file;
	private IDataProxy proxy;
	public XMLMgr(){
		reader=new XmlReader();
	}
	public static synchronized XMLMgr getInstance(){
		if(instance==null)
			instance=new XMLMgr();
		return instance;
	}
	
	public void setProxy(IDataProxy proxy) {
		this.proxy=proxy;
	}
	
	@Override
	public void setFile(String file) {
		// TODO Auto-generated method stub
		this.file=file;
	}
	
	@Override
	public void writeXML(String xml) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Element readXML() {
		// TODO Auto-generated method stub
		root=reader.parse(proxy.getNotesInfo());
		return root;
	}
	
	public void parseXML(String xml){
		root=reader.parse(xml);
		//return root;
	}
	
	@Override
	public void saveXML(String file) {
		// TODO Auto-generated method stub
		try {
			writer=new XmlWriter(new FileWriter(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void saveXML(Element element) {
		// TODO Auto-generated method stub
		try {
			writer=new XmlWriter(new FileWriter(Config.songPath+this.file));
			writer.text(element.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void saveXML() {
		// TODO Auto-generated method stub
		
	}
	
	
}
