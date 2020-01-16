
package com.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class ThreadEx1{
	
	public static void main(String[] args) {
		
		ArrayList<String> qualifications = new ArrayList<String>();
		qualifications.add("b.tech");qualifications.add("Xstd");qualifications.add("Inter");qualifications.add("btech cse");qualifications.add("mtechcse"); 
		qualifications.add("b.sc");qualifications.add("inter bipc");qualifications.add("msc-physics");qualifications.add("ms-chemistry");qualifications.add("ph.d");
		
		HashMap<Integer,Employee> employees = new HashMap<Integer,Employee>();
		for(int i=0;i<10;i++){
			employees.put(i, new Employee("nagaraju"+i, qualifications.get(i), "m", 10000.00));	
		}
		
		//Employee e1 = new Employee("nag", "tech arch", "male", 10000.00);
		
		/*Thread mt1= new Thread(new MyTask1());
		Thread mt2 = new Thread(new MyTask1("Mt2.class","Btech","M"));
		mt1.start();
		mt2.start();
		*/
		
		ExecutorService executor = Executors.newFixedThreadPool(4); 
		for(int index=0; index<10;index++){
			
			executor.execute(new Thread(new MyTask2(((Employee) employees.get(index)).getName(), 
					((Employee) employees.get(index)).getQualification(), ((Employee) employees.get(index)).getGender())));
		} 
		executor.shutdown();
		while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
		
		
	}
	
	
}

class Employee{
	public Employee(String name, String qualification, String gender, double salary) {
		super();
		this.name = name;
		this.qualification = qualification;
		this.gender = gender;
		this.salary = salary;
	}
	String name, qualification, gender;
	double salary;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	

}

class MyTask1 implements Runnable{
	public MyTask1(String name, String qualification, String gender) {
		super();
		this.name = name;
		this.qualification = qualification;
		this.gender = gender;
	}

	public MyTask1() {
		super();
		this.name = "no-name";
		this.qualification = "no-qualification";
		this.gender = "no-gender";
	}

	static int counter=0;
	
	String name, qualification, gender;

	
	public static int getCounter() {
		return counter;
	}


	public static void setCounter(int counter) {
		MyTask1.counter = counter;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public void run() {
		try {
			counter++;
			System.out.println("Thread was invoked:static counter value:" + counter);			
			Thread.sleep(10000);
			System.out.println("ThreadName:"+getName());
			System.out.println("ThreadGender:"+getGender());
			System.out.println("ThreadQualification"+getQualification());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}		
}

class MyTask2 implements Runnable{
	String name, qualification, gender;
	
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public MyTask2(String name, String qualification, String gender) {
		super();
		this.name = name;
		this.qualification = qualification;
		this.gender = gender;
	}	
	int counter=0;
	
	
	@Override
	public void run() {
		try {
			//counter++;
			//System.out.println("Thread:"+counter);
			System.out.println("Thread:"+Thread.currentThread().getName()+"  -  Employee Name: "+getName() +", Gender:"+ getGender() +", Qualification:"+getQualification()+"\n");
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}		
}