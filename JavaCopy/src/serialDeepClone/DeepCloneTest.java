package serialDeepClone;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
//�������л��������
//��clone
 
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
		Student student2 = (Student) student1.deepCopy();
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
 
class Teacher implements Serializable
{
 
	private static final long serialVersionUID = -8834559347461591191L;
 
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
 
}
 
class Student implements Serializable
{
 
	// serialVersionUID
	// �����Ķ������л���浽Ӳ������󣬿��Ǻ�����ȴ���������field(���ӻ���ٻ����)�����㷴���л�ʱ���ͻ����Exception�ģ������ͻ���ɲ������Ե����⡣
	// ����serialVersionUID��ͬʱ�����ͻὫ��һ����field��type��ȱʡֵ��ֵ(��int�͵���0,String�͵���null��)��������Աܿ��������Ե����⡣������ø�serialVersionUID��ֵ
	private static final long serialVersionUID = 7991552226614088458L;
 
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
 
	public Object deepCopy() throws Exception
	{
		// ���ö������л�����,��Ϊд��������Ƕ����һ����������ԭ������Ȼ������JVM���档��������������Կ���ʵ�ֶ�������
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
 
		ObjectOutputStream oos = new ObjectOutputStream(bos);
 
		oos.writeObject(this);
 
		// �������л��ɶ���
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
 
		ObjectInputStream ois = new ObjectInputStream(bis);
 
		return ois.readObject();
	}
}
 
/*������Ϊ��
20
zhangsan
~~~~~~~~~~~~~~~~~~~~~~
40
Teacher zhang
~~~~~~~~~~~~~~~~~~~~~~
40
Teacher zhang
*/