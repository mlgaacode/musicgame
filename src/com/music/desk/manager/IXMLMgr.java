package com.music.desk.manager;

import com.badlogic.gdx.utils.XmlReader.Element;

public interface IXMLMgr {
	void setFile(String file);
	void writeXML(String xml);
	Element readXML();
	void saveXML(String file);
	void saveXML();
	void saveXML(Element element);
}
