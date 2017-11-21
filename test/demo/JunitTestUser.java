package demo;

import com.jxgis.ffireserver.bean.FFPArtificiallake;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.service.AreaService;
import com.jxgis.ffireserver.service.ArtificiallakeService;
import com.jxgis.ffireserver.web.service.SystemService;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class JunitTestUser extends BaseJunit4Test{
	private static final Logger logger = LoggerFactory.getLogger(JunitTestUser.class);

	@Autowired
	private SystemService systemService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private ArtificiallakeService artificiallakeService;



	@Test
	public void saveTest() throws Exception {

		int i=1;
		List<Map<String, Object>> list = artificiallakeService.findForJdbc("SELECT * FROM ffp_area_code_boundary WHERE `code` IN (SELECT `code` FROM ffp_area_code_boundary GROUP BY `code` HAVING COUNT(`code`) > 1) ORDER BY `code`");
		for(Map<String, Object> map: list){

			if (i % 2 == 1){
				Map<String, Object> map_prev = list.get(i-1);
				if (map_prev.get("boundary").equals(map.get("boundary"))){
					artificiallakeService.executeSql("DELETE FROM `forest_fire_db`.`ffp_area_code_boundary` WHERE `code`=? limit 1", map.get("code"));
				}
			}

			i++;
		}
	}

	@Test
	public void readTest() throws Exception{
		ResourceBundle resource = ResourceBundle.getBundle("system");

		String hostName = resource.getString("xmpp.hostName");
		String port = resource.getString("xmpp.port");

		System.out.println(hostName);
		System.out.println(port);

	}

	@Test
	public void readTestdd() throws Exception{

		System.out.println(1%2);

	}



	@Test
	public void keyTest() throws Exception{

		FFPArtificiallake artificiallake = new FFPArtificiallake();
		artificiallake.setName("3232");
		artificiallake.setPac("450000");

		ReturnBody body = artificiallakeService.addArtificiallake(artificiallake, null);

		System.out.println(body.getStatus());
		System.out.println(body.getMsg());
	}

	@Test
	public void areaTest() throws Exception{
		FFPArtificiallake artificiallake = new FFPArtificiallake();
		artificiallake.setName("3232");
		artificiallake.setPac("450000");
		WKTReader fromText = new WKTReader();
		Geometry shape = fromText.read("POINT(50 60)");

//		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
//
//		Coordinate coord = new Coordinate(1, 1);
//		Point point = geometryFactory.createPoint(coord);

//		artificiallake.setShape((Point) shape);
//		ReturnBody body = artificiallakeService.addArtificiallake(artificiallake, null);
//		System.out.println(body.getStatus());
//		System.out.println(body.getMsg());
	}


	public static void main(String[] args){

		System.out.println(0%2);

	}

}
