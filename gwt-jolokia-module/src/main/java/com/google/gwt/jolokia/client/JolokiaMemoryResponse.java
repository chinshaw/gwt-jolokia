package com.google.gwt.jolokia.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JolokiaMemoryResponse extends JolokiaReadResponse {

	public static class UsageResponse {

		@JsonProperty("NonHeapMemoryUsage")
		private NonHeapMemoryUsage nonHeapMemoryUsage;

		public NonHeapMemoryUsage getNonHeapMemoryUsage() {
			return nonHeapMemoryUsage;
		}

		public void setNonHeapMemoryUsage(NonHeapMemoryUsage nonHeapMemoryUsage) {
			this.nonHeapMemoryUsage = nonHeapMemoryUsage;
		}
	}

	public static class NonHeapMemoryUsage {
		private int max;

		private int committed;

		private int init;

		private int used;

		public int getMax() {
			return max;
		}

		public void setMax(int max) {
			this.max = max;
		}

		public int getCommitted() {
			return committed;
		}

		public void setCommitted(int committed) {
			this.committed = committed;
		}

		public int getInit() {
			return init;
		}

		public void setInit(int init) {
			this.init = init;
		}

		public int getUsed() {
			return used;
		}

		public void setUsed(int used) {
			this.used = used;
		}

	}

	private UsageResponse value;

	public UsageResponse getValue() {
		return value;
	}

	public void setValue(UsageResponse value) {
		this.value = value;
	}
	
	public NonHeapMemoryUsage getNonHeapMemoryUsage() {
		return value.getNonHeapMemoryUsage();
	}
}
