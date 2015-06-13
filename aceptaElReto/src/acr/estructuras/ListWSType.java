package acr.estructuras;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListWSType<T> implements List<T>{
	
	private List<T> listaInterna;
	
	
	
	@Override
	public void add(int location, T object) {
		// TODO Auto-generated method stub
		this.listaInterna.add(location, object);
	}

	@Override
	public boolean add(T object) {
		// TODO Auto-generated method stub
		return this.listaInterna.add(object);
	}

	public List<T> getListaInterna() {
		return listaInterna;
	}

	public void setListaInterna(List<T> listaInterna) {
		this.listaInterna = listaInterna;
	}

	@Override
	public boolean addAll(int location, Collection<? extends T> collection) {
		// TODO Auto-generated method stub
		return this.listaInterna.addAll(location, collection);
	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		// TODO Auto-generated method stub
		return this.listaInterna.addAll(collection);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.listaInterna.clear();
	}

	@Override
	public boolean contains(Object object) {
		// TODO Auto-generated method stub
		return this.listaInterna.contains(object);
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		// TODO Auto-generated method stub
		return this.listaInterna.containsAll(collection);
	}

	@Override
	public T get(int location) {
		// TODO Auto-generated method stub
		return this.listaInterna.get(location);
	}

	@Override
	public int indexOf(Object object) {
		// TODO Auto-generated method stub
		return this.listaInterna.indexOf(object);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.listaInterna.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return this.listaInterna.iterator();
	}

	@Override
	public int lastIndexOf(Object object) {
		// TODO Auto-generated method stub
		return this.listaInterna.lastIndexOf(object);
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return this.listaInterna.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int location) {
		// TODO Auto-generated method stub
		return this.listaInterna.listIterator(location);
	}

	@Override
	public T remove(int location) {
		// TODO Auto-generated method stub
		return this.listaInterna.remove(location);
	}

	@Override
	public boolean remove(Object object) {
		// TODO Auto-generated method stub
		return this.listaInterna.remove(object);
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		// TODO Auto-generated method stub
		return this.listaInterna.removeAll(collection);
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		// TODO Auto-generated method stub
		return this.listaInterna.retainAll(collection);
	}

	@Override
	public T set(int location, T object) {
		// TODO Auto-generated method stub
		return this.listaInterna.set(location, object);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.listaInterna.size();
	}

	@Override
	public List<T> subList(int start, int end) {
		// TODO Auto-generated method stub
		return this.listaInterna.subList(start, end);
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return this.listaInterna.toArray();
	}

	@Override
	public <T> T[] toArray(T[] array) {
		// TODO Auto-generated method stub
		return this.listaInterna.toArray(array);
	}

	
}
