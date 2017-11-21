package com.jxgis.ffireserver.util;

/**
 * Created by YanWei on 2017/6/19.
 *
 * pac工具
 *
 */
public class FormatPac {

	public static String formatPac(String str) throws Exception {
		try {
			Object province = Integer.parseInt( str.substring(0,2) ) > 0 ? str.substring(0,2) : "";
			Object city = Integer.parseInt( str.substring(2,4) ) > 0 ? str.substring(2,4) : "";
			Object county = Integer.parseInt( str.substring(4,6) ) > 0 ? str.substring(4,6) : "" ;
			Object township = "" ;
			if (str.length() > 6) {
				township = Integer.parseInt( str.substring(6,9) ) > 0 ? str.substring(6,9) : "";
			}

			Object village = "";
			if (str.length() > 9){
				village = Integer.parseInt( str.substring(9,12) ) > 0 ? str.substring(9,12) : "";
			}

			Object village_1 = "";
			if (str.length() > 12){
				village = Integer.parseInt( str.substring(12,15) ) > 0 ? str.substring(12, 15) : "";
			}
			Object village_2 = "";
			if (str.length() > 15){
				village = Integer.parseInt( str.substring(15, 18) ) > 0 ? str.substring(15, 18) : "";
			}

			String str_ = "";

			if ( province instanceof String ) {
				str_ += province;
			}
			if ( city instanceof String ) {
				str_ += city;
			}
			if ( county instanceof String ) {
				str_ += county;
			}
			if ( township instanceof String ) {
				str_ += township;
			}
			if ( village instanceof String ) {
				str_ += village;
			}
			if ( village_1 instanceof String ) {
				str_ += village_1;
			}
			if ( village_2 instanceof String ) {
				str_ += village_2;
			}

			return str_;

		} catch (Exception e) {

			return "";

		}
	}

	public static String formatPacUnderline(String str) throws Exception {
		String str_ = formatPac( str );
		switch (str_.length()){
			case 0: return str_ + "__0000";
			case 2: return str_ + "____";
			case 4: return str_ + "__";
			case 6: case 9:case 12: case 15: return str_ + "___";
			default:return str_;
		}
	}
	public static String formatPacUnderline(String str, boolean flog) throws Exception {
		String str_ = formatPac( str );
		switch (str_.length()){
			case 0: return str_ + "__0000";
			case 2: return str_ + "__00";
			case 4: return str_ + "__";
			case 6:case 9: return str_ + "___";
			case 12:case 15:
				if (!flog){
					return str_ + "___";
				}
				return str_;
			default:return str_;
		}
	}
	public static String repairPac(String str) {
		switch (str.length()){
			case 2: return str += "0000";
			case 4: return str += "00";
			default: return str;
		}
	}


	public static void main(String[] args) throws Exception{
		//450102001003
//		System.out.println( formatPac("450102001000") );
//		System.out.println( formatPacUnderline("360100") );

		System.out.println(FormatPac.formatPacUnderline( "451603", false));
	}
}
