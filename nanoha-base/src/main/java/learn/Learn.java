package learn;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class Learn {

	public static void main(String[] args) {
		String xs = "57575768864302403850380462324323543654776583433683820292512484657849089281";
		xs = StringUtils.reverse(xs);
		String y = "";
		for (int i = 0, n = xs.length(); i < xs.length(); i++, n--) {
			String x = xs.charAt(i) + "";
			if (n % 3 == 0) {
				y += x + ",";
			} else {
				y += x;
			}
		}

		System.out.println(StringUtils.reverse(y));
		y();

	}

	static void y() {
		String xs = "57575768864302403850380462324323543654776583433683820292512484657849089281";
		String xs1 = StringUtils.reverse(xs);
		String y = "";
		String y1 = "";
		for (int i = 0, n = xs.length(); i < xs.length(); i++, n--) {
			String x = xs.charAt(i) + "";
			y += x + LearnConfigUtils.getInstance().getString(n + "");
			if (n % 3 == 0) {
				y1 += x + ",";
			} else {
				y1 += x;
			}
		}

		System.out.println(y);
		System.out.println(y1);
	}

	static void x() {
		BigDecimal x = new BigDecimal("10");

		BigDecimal y = new BigDecimal("1");

		que10(x, y, 0, 0);
	}

	static void que10(BigDecimal x, BigDecimal y, int n, int k) {
		for (int i = 0; i < k + 4; i++) {
			y = x.multiply(y);
		}
		System.out.print("\"" + y.toString().length() + "\",");
		if (n == 18) {
			return;
		} else {
			n++;
			que10(x, y, n, k);
		}
	}
}
