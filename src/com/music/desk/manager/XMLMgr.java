package com.music.desk.manager;

import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
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
	private IDataProxy proxy=null;
	private Element root=null;
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
	public Element readXML() {
		// TODO Auto-generated method stub
		if(root==null)
			root=reader.parse(proxy.getNotesInfo());
		return root;
	}
	public void setXML(Element root){
		this.root=root;
	}
	@Override
	public void saveXML(Element element) {
		// TODO Auto-generated method stub
		proxy.setNotesInfo(element.toString());
	}
	public IDataProxy getProxy(){
		return proxy;
	}
	@Override
	public void saveXML() {
		// TODO Auto-generated method stub
		proxy.setNotesInfo(root.toString());
	}
	
}
