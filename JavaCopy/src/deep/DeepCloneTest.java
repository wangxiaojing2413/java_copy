package deep;
public class DeepCloneTest
{
 
	public static void main(String[] args) throws Exception
	{
		// teacher���󽫲���clone������Student������.
		Teacher teacher = new Teacher();
		teacher.setAge(40);
		teacher.setName("Teacher zhang");
 
		Student student1 = new Student();
		student1.setAge(20);
		student1.setName("zhangsan");
		student1.setTeacher(teacher);
 
		// ���Ƴ���һ������student2
		Student student2 = (Student) student1.clone();
		System.out.println(student2.getAge());
		System.out.println(student2.getName());
 
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(student1.getTeacher().getAge());
		System.out.println(student1.getTeacher().getName());
 
		// �޸�student2�����ö���
		student2.getTeacher().setAge(50);
		student2.getTeacher().setName("Teacher Li");
 
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(student1.getTeacher().getAge());
		System.out.println(student1.getTeacher().getName());
	}
}
 
class Teacher implements Cloneable
{
	public int age;
	public String name;
 
	public int getAge()
	{
		return age;
	}
 
	public void setAge(int age)
	{
		this.age = age;
	}
 
	public String getName()
	{
		return name;
	}
 
	public void setName(String name)
	{
		this.name = name;
	}
 
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
 
}
 
class Student implements Cloneable
{
 
	public int age;
	public String name;
	public Teacher teacher;
 
	public int getAge()
	{
		return age;
	}
 
	public void setAge(int age)
	{
		this.age = age;
	}
 
	public String getName()
	{
		return name;
	}
 
	public void setName(String name)
	{
		this.name = name;
	}
 
	public Teacher getTeacher()
	{
		return teacher;
	}
 
	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}
 
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		Student student = (Student) super.clone();
		// �����õĶ���teacherҲclone��
		student.setTeacher((Teacher) (student.getTeacher().clone()));
		return student;
	}
}
/* 
������Ϊ��
20
zhangsan
~~~~~~~~~~~~~~~~~~~~~~
40
Teacher zhang
~~~~~~~~~~~~~~~~~~~~~~
40
Teacher zhang
*/