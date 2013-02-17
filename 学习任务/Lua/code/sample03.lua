
--[[
a = "one string"
b = string.gsub(a, "one", "another") -- �޸��ַ���
print(a)
print(b)
print()


print("one line\nnext line\n\"in quotes\", 'in quotes'")

print('a simple way \'\\\'')
--]]

-- lua �Զ�ת������
--[[
print("10" + 1)
print("10 + 1")
print("-5.3e-10" * "2")


line = io.read()  -- ��ȡһ��
n = tonumber(line);
if n == nil then
	error(line .. " is not a valid number")
else
	print(n * 2)
end



a = "hello"
print(#a)  -- # ����ַ������ȣ������Ȳ�����
print(#"good\0byte")


--  ����һ�� table�����ҽ����ô洢��a
a = {}
k = "x"
a[k] = 10  -- key="x" value = 10
a[20] = "great"  -- key=20 value = "great"
print(a["x"]); --> 10
k = 20
print(a[k]);  --> great
a["x"] = a["x"] + 1;
print(a["x"]);  --> 11



--  ����һ�� table�����ҽ����ô洢��a
a = {}
--  ����1000������Ŀ
for i = 1, 1000
	do a[i] = i * 2
end
print(a[9])  --> 18
--a["x"] = 10
a.x = 10  -- a["x"] = 10 ��ͬ�� a.x = 10
print(a["x"]) --> 10
print(a["y"]) --> nil



a = {}  -- �� 1 Ϊ������ʼֵ
a[10000] = 2;
print(table.maxn(a))  --> 10000
print(a[10000])
print(#a)
for i = 1, 1000
	do a[i] = i * 2
end
print(#a) --> 1000
print(#a + 1)  --> 1001



-------- 10��"10"��"+10" �ǲ�ͬ������ -------
i = 10; j = "10"; k = "+10"
a = {}
a[i] = "one value"
a[j] = "another value"
a[k] = "yet another value"
print(a[i])
print(a[j])
print(a[k])
print(a[tonumber(j)])
print(a[tonumber(k)])

--]]
