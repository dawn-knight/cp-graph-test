package com.dk.cp.graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;

import it.uniroma1.dis.wsngroup.gexf4j.core.EdgeType;
import it.uniroma1.dis.wsngroup.gexf4j.core.Gexf;
import it.uniroma1.dis.wsngroup.gexf4j.core.Graph;
import it.uniroma1.dis.wsngroup.gexf4j.core.Mode;
import it.uniroma1.dis.wsngroup.gexf4j.core.Node;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.Attribute;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeClass;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeList;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeType;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.GexfImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.StaxGraphWriter;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.data.AttributeListImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.viz.PositionImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.NodeShape;
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.Position;

public class Demo {

	public static void main(String[] args) {
		Gexf gexf = new GexfImpl();
		Calendar date = Calendar.getInstance();
		gexf.getMetadata().setCreator("dawn_knight")
				.setLastModified(date.getTime());
		gexf.setVisualization(true);

		Graph graph = gexf.getGraph();
		graph.setDefaultEdgeType(EdgeType.DIRECTED).setMode(Mode.STATIC);

		AttributeList attrList = new AttributeListImpl(AttributeClass.NODE);
		graph.getAttributeLists().add(attrList);

		Attribute a = attrList.createAttribute("0", AttributeType.INTEGER,
				"likes");

		Node node0 = graph.createNode("0");
		node0.setLabel("idea")//.setSize(20)
				.setPosition(new PositionImpl(100, 100, 0));
		node0.getShapeEntity().setNodeShape(NodeShape.IMAGE).setUri("http://localhost:8080/tomcat.gif");

		Node node1 = graph.createNode("1");
		node1.setLabel("suggestion").setSize(10)
				.setPosition(new PositionImpl(200, 200, 0))
				.getAttributeValues().addValue(a, "100");

		node0.connectTo("0", "", EdgeType.DIRECTED, node1);

		StaxGraphWriter graphWriter = new StaxGraphWriter();
		File f = new File("demo.gexf");
		Writer out;
		try {
			out = new FileWriter(f);
			graphWriter.writeToStream(gexf, out, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
