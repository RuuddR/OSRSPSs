import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import java.util.Enumeration;

public class BreakHandler extends RandomHandler {

	private String confFile = "Configs/breaks.txt";
	private Configuration conf = new Configuration(rs.configFile);

	private long startTime = System.currentTimeMillis();
	private ArrayList<Break> breaks = new ArrayList<Break>();
	private Iterator it;
	private Break curBreak;
	private boolean reset;

	public BreakHandler() {
		confFile = conf.get("breaks", confFile);
		conf = new Configuration(confFile);

		for (Enumeration e = conf.propertyNames(); e.hasMoreElements();) {
			String prop = (String) e.nextElement();
			String val = conf.get(prop);
			String breakVal = val.substring(0, val.indexOf(',')).trim();
			String lengthVal = val.substring(val.indexOf(',') + 1).trim();
			long breakAtMin, breakAtMax, lengthMin, lengthMax;

			try {
				if (breakVal.indexOf('|') != -1) {
					breakAtMin = Long.parseLong(
							breakVal.substring(0, breakVal.indexOf('|')).trim());
					breakAtMax = Long.parseLong(
							breakVal.substring(breakVal.indexOf('|') + 1).trim());
				} else {
					breakAtMax = Long.parseLong(breakVal);
					breakAtMin = breakAtMax - breakAtMax / 4;
				}

				if (lengthVal.indexOf('|') != -1) {
					lengthMin = Long.parseLong(
							lengthVal.substring(0, lengthVal.indexOf('|')).trim());
					lengthMax = Long.parseLong(
							lengthVal.substring(lengthVal.indexOf('|') + 1).trim());
				} else {
					lengthMax = Long.parseLong(lengthVal);
					lengthMin = lengthMax / 2;
				}
			} catch (Exception ex) {
				log("Exception loading BreakHandler config (" + prop + " = " + val + ")");
				ex.printStackTrace();
				continue;
			}

			// convert to ms
			breakAtMin *= 60000;
			breakAtMax *= 60000;
			lengthMin *= 60000;
			lengthMax *= 60000;

			Break b = new Break(breakAtMin, breakAtMax, lengthMin, lengthMax);
			breaks.add(b);
		}

		Collections.sort(breaks,
			new Comparator<Break>() {
					public int compare(Break b1, Break b2) {
						return (int) (b1.getBreakAtMin() - b2.getBreakAtMin());
					}
			});

		it = breaks.iterator();
	}

	public long run(long ticks) {
		if (breaks.isEmpty())
			return -1;

		if (reset) {
			it = breaks.iterator();
			startTime = System.currentTimeMillis();
			reset = false;
		}

		if (curBreak == null)
			curBreak = (Break) it.next();

		long curTime = System.currentTimeMillis();
		if (curBreak.shouldBreak(startTime, curTime)) {
			if (curBreak.getLengthMin() <= 0) {
				log("After " + cTime(curTime - startTime) + ", shutting down.");
				shutdown();
			} else {
				long breakLength = curBreak.randLength();
				log("After " + cTime(curTime - startTime)
						+ ", taking break for " + cTime(breakLength));
				forceLogout((int) breakLength);
			}

			curBreak = null;
			if (!it.hasNext())
				reset = true;

			// give the bot ample time to log out, so run() isn't called
			// again and values reset before the break has been taken.
			return random(5000, 10000);
		}

		return -1;
	}

	private String cTime(long eTime) {
		long hrs = eTime / 1000 / 3600;
		eTime -= (hrs * 3600 * 1000);
		long mins = eTime / 1000 / 60;
		eTime -= (mins * 60 * 1000);
		long secs = eTime / 1000;
		return String.format("%1$02d:%2$02d:%3$02d", hrs, mins, secs);
	}

	protected class Break {
		private final long breakAtMin;
		private final long breakAtMax;
		private final long lengthMin;
		private final long lengthMax;

		Break(long breakAtMin, long breakAtMax, long lengthMin, long lengthMax) {
			this.breakAtMin = breakAtMin;
			this.breakAtMax = breakAtMax;
			this.lengthMin = lengthMin;
			this.lengthMax = lengthMax;
		}

		private long randLong(long min, long max) {
			return min + (long) (java.lang.Math.random() * (max - min));
		}

		public long getBreakAtMin() {
			return breakAtMin;
		}

		public long getBreakAtMax() {
			return breakAtMax;
		}

		public long getLengthMin() {
			return lengthMin;
		}

		public long getLengthMax() {
			return lengthMax;
		}

		public long randBreakAt() {
			return randLong(breakAtMin, breakAtMax);
		}

		public long randLength() {
			return randLong(lengthMin, lengthMax);
		}

		public boolean shouldBreak(long startTime, long curTime) {
			if ((curTime - startTime) > randBreakAt())
				return true;
			else
				return false;
		}
	}
}
