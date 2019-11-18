

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Genetransfer {
	public static int maxlength = 23;
	public static ArrayList<String> init;
	public static ArrayList<String> fin;
	public static ArrayList<Integer> initindex;
	public static ArrayList<Integer> finindex;
	public static int position;
	public static int lent;
    public static int direct;
    public static ArrayList<Double> initcg;
    public static ArrayList<Double> fincg;
    public static ArrayList<Double> inittm;
    public static ArrayList<Double> fintm;
    public static String gene;
    public static ArrayList<Integer[]> sort;
    public static ArrayList<String> sort0;
    public static ArrayList<String> aort0;
    public static ArrayList<Integer[]> initcomp;
    public static ArrayList<Integer[]> fincomp;
    
public static void main(String[] args){
	init = new ArrayList<String>();
	fin = new ArrayList<String>();
	initindex = new ArrayList<Integer>();
	finindex = new ArrayList<Integer>();
	initcg = new ArrayList<Double>();
	fincg = new ArrayList<Double>();
	inittm = new ArrayList<Double>();
	fintm = new ArrayList<Double>();
	sort = new ArrayList<Integer[]>();
	initcomp = new ArrayList<Integer[]>();
	fincomp = new ArrayList<Integer[]>();
	sort0 = new ArrayList<String>();
	aort0 = new ArrayList<String>();
// enter the information around the gene locus u want to pcr:
//use D to notify that locus
//here is an example of rs1800414
	gene = "CCCTCCCCCT ATGACCAGGT GGATTCTCGC GGGTGTCATT CCAGCGCTAA GAGTGCACCC\r\n"+  
			" CCTGCATTCC AGGGGCCTCA TGCATGTCGT GTAAAGAACA TGGCCAGAGC CTTATCTGGG\r\n" + 
			" AGTCACAGTT CCGTGAGAAG GCTCAGCCTC ATGGCCCCAC CCGCATGCTT GGCGTGGTAG\r\n" + 
			" AGAAAGGAAC AGTGAAGACA GCATAGGCCC CTGTAGAAAC GCCCCGTCAT CCTCTGATAC\r\n" + 
			" CTGCCGGCCA GGTGTTTCAT AACAGGGCTG TGCTACTCTT GACATCTGTG TTTATCTTTC\r\n" + 
			" ATAAAGATTT TGAATGCAGT AATCCTGAAT CTGTACGGGT TTCCTTGTAA CACAGTACTT\r\n" + 
			" TGCCATTTTC TTTCAAGTTC GAGAGGTTAC ATTTTTCATC CTCGTGAAAT CTGTCGTGAT\r\n" + 
			" TCCAGTTGCG TAGGTTATGA CACGCTGCAG GAGTCAGAAG GTTGTGCAGA GTAAATGAGC\r\n" + 
			" TGTGGTTTCT CTCTTACAGC\r\n" + 
			" D\r\n" + 
			" TAGGATATCT GACGGGATTC TGCTCGCCAA ATGCCTGACA GTSTTGSGAT TTGTTATCTT\r\n" + 
			" CATGTTTTTC CTCAATTCGT TTGTCCCTGG CATTCATCTT GATCTTGGTG AGTCTAATTT\r\n" + 
			" AGCTTTGGTT CATAGGCTTT GTCACATTCT GGATGGGAAG GTTTCAGAGC CTGTTCCCAG\r\n" + 
			" ACACTGACTT TGCCCACAGG CAGCCGGGCT GGTGGAAGGC CAGAGAGGGC TGAGATGGAG\r\n" + 
			" GGTGGGCAGC CTGCCCTGGG AAGAAGGGCG CCTTTCCTTT TGGTTTCCTG GGCAGGAGGG\r\n" + 
			" AGGGAGAGAG AGATGCATCT CTGGCCCCTT AGACTCTGTG CCATGGGTCC TCAGCCCCTC\r\n" + 
			" CAGGGATGAC CATGAGGAGG AATATAGAGT GGGCACTGTC CTGTCTATTG TAGTTAATAA\r\n" + 
			" CCACATCTTT ACATGGTTCC CAGAAGAGAT GGAGCCACAT GGGCAAGGCC AGCGCTGCCA\r\n" + 
			" TCTGTGCCGC CTACCATGCC";  
	gene = gene.replaceAll(" ", "");
	gene = gene.replaceAll("\n", "");
	gene = gene.replaceAll("\r", "");
	//System.out.println(gene);
	lent = gene.length();
	String regene = reverse(gene);
	direct = gene.indexOf("D");
	int redirect = regene.indexOf("D");
//	System.out.println(direct);
	String gene1 = regene.substring(redirect - 500, redirect-20);
	String gene2 = regene.substring(redirect + 20, redirect+500);
	position = 0;
	search(gene1,17,init,initindex,initcg,inittm,0);
	position = 0;
	search(gene2,17,fin,finindex,fincg,fintm,1);
     for(int srr=0;srr<init.size();srr++)
    	{System.out.println(init.get(srr));
    	System.out.println(inittm.get(srr));}
     System.out.println();
     for(int srr=0;srr<fin.size();srr++)
 	{System.out.println(fin.get(srr));
 	System.out.println(fintm.get(srr));}
//     deletes();
    fitbetween();

}

public static void deletes()
{ArrayList<Integer> u = new ArrayList<Integer>();
ArrayList<String> u0 = new ArrayList<String>();
//form: delete a forward primer: u0.add("...");
ArrayList<Integer> o = new ArrayList<Integer>();
ArrayList<String> o0 = new ArrayList<String>();
//form: delete a reverse primer: o0.add("...");
for(int j=0;j<u0.size();j++) {
int i = init.indexOf(u0.get(j));
init.remove(i);
inittm.remove(i);
initcg.remove(i);
//initcomp.remove(i);
initindex.remove(i);
}
for(int j=0;j<o0.size();j++) {
int i = fin.indexOf(o0.get(j));
fin.remove(i);
fintm.remove(i);
fincg.remove(i);
//initcomp.remove(i);
finindex.remove(i);
}
//delete2: use to delete a pair of primer
//delete2(forward,reverse);
}

public static void delete2(String ks, String ks2)
{
	int i = init.indexOf(ks);
	int j = fin.indexOf(ks2);
	String[] temp = {ks,ks2};
	sort0.add(ks);
	aort0.add(ks2);
}

public static void adds(ArrayList<Integer[]> a1,int index,int i1,int i2)
{
     Integer[] temp = {i1,i2};
     a1.add(index,temp);
}


public static String reverse(String a)    //simply reverse
{
	String b = "";
	for(int i = a.length()-1;i>=0;i--)
	{
		b = b+a.charAt(i);
	}
	return b;
}

public static String reverse2(String a)  //reverse and get the Complementary Nucleobase

{
	String b = "";
	for(int i = a.length()-1;i>=0;i--)
	{
		b = b+junction(a.charAt(i));
	}
	return b;
}

public static char junction(char a) //get Complementary base
{    switch(a) {
	case 'A':
	return 'T';
	case 'T':
    return 'A';
	case 'C':
	return 'G';
	case 'G':
	return 'C';
	default:
	return a;
    }
	}

public static boolean score(String s,int ss)  //calculate the score of each primer
{
	String sa = s.substring(s.length()-5);
	int spo = 0;
	int spo2 = 0;
	if(ss == 0)
	for(int i=0;i<gene.length()-4;i++) {
		if(junction(gene.charAt(i))==sa.charAt(4))
			if(junction(gene.charAt(i+1))==sa.charAt(3))
				if(junction(gene.charAt(i+2))==sa.charAt(2))
					if(junction(gene.charAt(i+3))==sa.charAt(1))
						if(junction(gene.charAt(i+4))==sa.charAt(0))
							spo++;}
	if(spo == 1&&ss == 0)
		return true;
	if(spo == 0&&ss == 1)
		return true;
	if(spo == 0&&ss==0)
	{ 
		return false;}
	return false;
}

public static void search(String a,int len,ArrayList<String> t,ArrayList<Integer> t1,ArrayList<Double> t2,ArrayList<Double> t3,int orn){ 
	//search for all the primer
	if(a.length()<=len+1){
		System.out.println("search...finished");
		System.out.println();
	}
	else {
	String z = "";
	for(int leng=len;leng<=maxlength;leng++){
	for(int i = 0;i<=Math.min(leng, a.length()-1);i++) {
		z = z+junction(a.charAt(i));
	}
	if(fit(z,orn)&&z.length()<=maxlength&&!z.contains("S")) {
	
		if(orn == 0){
		t.add(z);
		t1.add(gene.indexOf(reverse2(z)));}
		else {
		t.add(reverse2(z));
		t1.add(gene.indexOf(reverse2(z)));}	
		
		double ks = 0;
		for(int is=0;is<z.length();is++)
			if(z.charAt(is) == 'C'||z.charAt(is)=='G')
				ks++;
		double gcp = ks/z.length();
		double tm = ks*4+(z.length()-ks)*2;
		double tm0 = 81.5+0.41*gcp-600/z.length();
		t2.add(gcp);
		t3.add(tm0);
	//	System.out.println(z);
	}
	z = "";	}
	search(a.substring(1),len,t,t1,t2,t3,orn);}}


public static boolean fit(String a,int orn) {
//find whether the primer fits 
	if(orn ==1) 
		a=reverse2(a);
	if(a.charAt(0)=='G'||a.charAt(0)=='C')
	if(a.charAt(a.length()-1)=='G'||a.charAt(a.length()-1)=='C'||a.charAt(a.length()-1)=='T')
		{return fit1_gctm(a)&&fit2_gcclamp(a)&&fit3_hairpin(a)&&score(a,orn);}
	return false;
	}

public static boolean fit1_gctm(String a){  //chech whether g-c% and Tm is appropriate
	double k = 0;
	for(int i=0;i<a.length();i++)
		if(a.charAt(i) == 'C'||a.charAt(i)=='G')
			k++;
	double gcp = k/a.length();
	//double tm = k*4+(a.length()-k)*2;
	double tm0 = 81.5+0.41*gcp-600/a.length();
	//System.out.println(gcp+" "+tm);
	if(gcp>=0.4&&gcp<=0.6)
		if(tm0>=52&&tm0<=60)
			return true;
	return false;
}

public static boolean fit2_gcclamp(String a) {  
	//check whether there're not so many G and C at the beginning and the end
	for(int ks=0;ks<a.length()-3;ks++)
	if(a.charAt(ks)=='G'||a.charAt(ks)=='C')
		if(a.charAt(ks+1)=='G'||a.charAt(ks+1)=='C')
		  if(a.charAt(ks+2)=='G'||a.charAt(ks+2)=='C')
			  if(a.charAt(ks+3)=='G'||a.charAt(ks+3)=='C')
			//if(a.charAt(ks)==a.charAt(ks+3))
			return false;
	for(int ks=0;ks<a.length()-4;ks++)
		if(a.charAt(ks)==a.charAt(ks+1))
			if(a.charAt(ks)==a.charAt(ks+2))
			  if(a.charAt(ks)==a.charAt(ks+3))
				  if(a.charAt(ks)==a.charAt(ks+4))
				{
					  return false;}
	if(a.charAt(a.length()-1)=='G'&&a.charAt(a.length()-2)=='C')
		    return false;
	if(a.charAt(a.length()-2)=='G'&&a.charAt(a.length()-1)=='C')
			return false;
	return true;				
}

public static boolean fit3_hairpin(String a) {
	//check whether the primer will have hairpin problem
	String c = reverse(a).substring(0, 3);
	String rea = a;
	for(int i=0;i<rea.length()-5;i++) {
		if(junction(rea.charAt(i))==c.charAt(0))
			if(junction(rea.charAt(i+1))==c.charAt(1))
				if(junction(rea.charAt(i+2))==c.charAt(2)){
					return false;
					}
		}
	return true;
}

public static boolean fit3_hairpin_2(String a)
{
	String c = a.substring(0, 3);
	String c2 = a.substring(a.length()-3);
	String rea = reverse(a);
	for(int i=0;i<rea.length()-5;i++) {
		if(junction(rea.charAt(i))==c.charAt(0))
			if(junction(rea.charAt(i+1))==c.charAt(1))
				if(junction(rea.charAt(i+2))==c.charAt(2))
					return false;
		}
	for(int i=0;i<a.length()-5;i++) {
		if(junction(a.charAt(i))==c2.charAt(2))
			if(junction(a.charAt(i+1))==c2.charAt(1))
				if(junction(a.charAt(i+2))==c2.charAt(0))
					return false;
		}
	return true;
}

public static void fitbetween(){  //check all the primer pair will not very likely to combine with each other
		for(int i=0;i<init.size();i++)
		{String a1 = init.get(i);
		 int ip = initindex.get(i);
		 int dip = initindex.get(i)-direct;
		for(int j=0;j<fin.size();j++)
		{String b1 = fin.get(j);
		int fp = finindex.get(j);
		int dfp = direct - fp-b1.length();
		if(sort0.indexOf(a1)==-1||aort0.indexOf(b1)==-1)
		if(fit4_between(a1,b1,ip,fp)){
			Integer[] ts = {i,j,genegrade(a1),genegrade(b1)};
		    sort.add(ts);
			DecimalFormat df = new DecimalFormat(".00");
			System.out.println(a1);
			System.out.println("first position: "+ ip +", distance: "+ dip + "bp");
			System.out.println("cg-percent: "+df.format(initcg.get(i)*100)+"%, Tm: "+inittm.get(i));
			System.out.println("grade: "+genegrade(a1));
			System.out.println(b1);
		    System.out.println("second position: "+fp+ ", distance: "+ dfp + "bp");
			System.out.println("cg-percent: "+df.format(fincg.get(j)*100)+"%, Tm: "+fintm.get(j));
			System.out.println("grade: "+genegrade(b1));
			System.out.println();
		}
		}
		}
}

public static boolean fit4_between(String a,String b,int ip, int fp){ //check each two
	String c1 = reverse(reverse(a).substring(0, 3));
	String c2 = reverse(reverse(b).substring(0, 3));
	for(int i=0;i<a.length()-2;i++)
		if(junction(a.charAt(i))==c2.charAt(0))
			if(junction(a.charAt(i+1))==c2.charAt(1))
				if(junction(a.charAt(i+2))==c2.charAt(2)) {
					return false;
					}
	for(int i=0;i<b.length()-2;i++)
		if(junction(b.charAt(i))==c1.charAt(0))
			if(junction(b.charAt(i+1))==c1.charAt(1))
				if(junction(b.charAt(i+2))==c1.charAt(2)){
					return false;
					}
	double k1 = 0;
	double k2 = 0;
	for(int i=0;i<a.length();i++)
		if(a.charAt(i) == 'C'||a.charAt(i)=='G')
			k1++;
	for(int i=0;i<b.length();i++)
		if(b.charAt(i) == 'C'||b.charAt(i)=='G')
			k2++;
	double tm1 = k1*4+(a.length()-k1)*2;
	double tm2 = k2*4+(b.length()-k2)*2;
	if(Math.abs(tm1-tm2)>=5)
		return false;
    String result = gene.substring(fp-1,ip+a.length());
    double k0 = 0;
    for(int i=0;i<result.length();i++)
		if(result.charAt(i) == 'C'||result.charAt(i)=='G')
			k0++;
    double gcp = k0/result.length();
    double tm0 = 81.5+0.41*gcp-600/result.length();
    DecimalFormat df = new DecimalFormat(".00");
    String tm0s = df.format(tm0);
    if(result.length()<200||result.length()>500)
    	return false;
    System.out.println("product length: "+result.length());
	return true;
}

public static int genegrade(String s) {  //calc the grade
	int grade = 0;
	for(int i=0;i<s.length()-2;i++)
		if(s.charAt(i)==s.charAt(i+1)&&s.charAt(i)==s.charAt(i+2))
			grade++;
	for(int i=0;i<s.length()-2;i++)
			for(int j=i+2;j<s.length()-2;j++)
				if(s.charAt(i)==s.charAt(j))
					if(s.charAt(i+1)==s.charAt(j+1))
						if(s.charAt(i+2)==s.charAt(j+2))
							grade++;
return grade;
}
}
