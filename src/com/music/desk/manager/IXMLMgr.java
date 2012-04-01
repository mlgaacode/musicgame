package com.music.desk.manager;

import com.badlogic.gdx.utils.XmlReader.Element;

public interface IXMLMgr {
	Element readXML();
	void saveXML(Element element);
}
