package net.varek.rs2d.util;
 
import java.util.Iterator;
import java.util.Set;
import net.varek.rs2d.model.Entity;

public class EntityListIterator<E extends Entity> implements Iterator<E> {
	private Integer[] indicies;
	private Object[] entities;
	private EntityList entityList;
	private int curIndex = 0;

	public EntityListIterator(Object[] entities, Set<Integer> indicies, EntityList entityList) {
		this.entities = entities;
		this.indicies = indicies.toArray(new Integer[0]);
		this.entityList = entityList;
	}

	public boolean hasNext() {
		return indicies.length != curIndex;
	}

	public E next() {
		Object temp = entities[indicies[curIndex]];
		curIndex++;
		return (E) temp;
	}

	public void remove() {
		if (curIndex >= 1) {
			entityList.remove(indicies[curIndex - 1]);
		}
	}
}
