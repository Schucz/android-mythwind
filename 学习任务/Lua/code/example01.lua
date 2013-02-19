
--[[
--c1��c2�ǽ�����ͬһ�������ϣ���������ͬһ���ֲ������Ĳ�ͬʵ���ϵ�������ͬ�ıհ���
--�������������հ�ֵָ������ָ���������������Ǳհ���һ��ԭ��������
--������ˣ��ڲ��ᵼ�»�������������Ǽ���ʹ�����ﺯ����ָ�հ���
function newCounter()
    local i = 0
    return function()     -- anonymous function
       i = i + 1
        return i
    end
end
c1 = newCounter()
print(c1())  --> 1
print(c1())  --> 2
c2 = newCounter()
print(c2())  --> 1
print(c1())  --> 3
print(c2())  --> 2


-- �������� sample01 --
function foo(...)
	for i = 1, arg.n do
		print("Foo:" .. arg[i])
	end
end
foo("one", "two")

-- �������� sample02 --
function goo(t)
	for k,v in pairs(t) do
		print(k .. "=" .. v)
	end
end
goo({x=10, y=20})
--]]

--function contact(t)
	-- add the contact ��t��, which is
	-- stored as a table, to a database
--	for k,v in pairs(t) do
--		print(k .. "=" .. v)
--	end
--end
--���š���...�����Ǳ�ʾ�����ַ����ķ���
--contact {
--	name = "Game Developer",
--	email = "hack@ogdev.net",
--	url = "http://www.ogdev.net",
--	quote = [[
--		There are 10 types of people
--		who can understand binary.]]
--}
--contact {
	-- some other contact
--}

----- ����һ����� 5 λ��  --------
math.randomseed(os.time())
a = ""
for i=1, 5 do
b = math.random(0, 9)
a = a .. b
end
print(a)

--[[
�ŵ����⣺��һ�����ӣ��ӳ������3������ÿ���¶���һ�����ӣ�
С���ӳ�����3���º�ÿ��������һ�����ӣ��������Ӷ�������
��10���µ���������Ϊ���٣� 
��������� ���ӵĹ���Ϊ����1,1,2,3,5,8,13,21.... 
--]]
function fibo(i)
	
	if i > 2 then return fibo(i - 1) + fibo(i - 2)
	else return 1
	end
end
print(fibo(8))

--[[
��Ŀ���ж�101-200֮���ж��ٸ���������������������� 
�ж������ķ�������һ�����ֱ�ȥ��2��sqrt(�����)������ܱ������� ���������������������֮��������
--]]
function prime()
	result = ""
	for i = 101, 200 do
		b = true
		for j = 2, math.sqrt(i) do
			if i % j == 0 then
				b = false
				break
			end
		end
		if b then result = result .. " " .. i end
	end
	return result
end
print(prime())

--[[
��ӡ�����е� "ˮ�ɻ��� "����ν "ˮ�ɻ��� "��ָһ����λ�������λ���������͵��ڸ�������
���磺153��һ�� "ˮ�ɻ��� "����Ϊ153=1�����η���5�����η���3�����η��� 
�������������forѭ������100-999������ÿ�����ֽ����λ��ʮλ����λ
--]]
function shuixianhua()
	local str
	for i = 100,  999 do
		str = tostring(i)
		a = string.sub(str, 1, 1);
		b = string.sub(str, 2, 2);
		c = string.sub(str, 3, 3);
		if (tonumber(a) * tonumber(a) * tonumber(a) + 
			tonumber(b) * tonumber(b) * tonumber(b) + 
			tonumber(c) * tonumber(c) * tonumber(c)) == i 
		then
			print(i)
		end
	end
end
print("ˮ�ɻ���")
shuixianhua()

--[[
һ��������������100����һ����ȫƽ�������ټ���168����һ����ȫƽ���������ʸ����Ƕ��٣�
 1.�����������10�������жϣ��Ƚ���������100���ٿ������ٽ���������268���ٿ�����
 ���������Ľ�������������������ǽ��
--]]
function getNumber()
	for i = 1, 100000 do
		if (math.sqrt(i + 100) % 1 == 0) and (math.sqrt(i + 268) % 1 == 0) then
			print(i)
		end
	end
end
print("������")
getNumber()

--[[
�ж�ĳһ���Ƿ�������
--]]
function isLeapYear(ii)
	if (tonumber(ii) % 4 == 0) and (tonumber(ii) % 100 ~= 0)  or (tonumber(ii) % 400 == 0) then 
	return true
	else return false
	end
end
print("2012�Ƿ������ꣿ", isLeapYear(2012))

--[[
��1+2!+3!+...+20!�ĺ� 1.����������˳���ֻ�ǰ��ۼӱ�����۳ˡ� 
--]]
print("��׳��ۼ�:")
function factorial(i)
	local sum
	sum = 1
	for v = 1, i do
		sum = sum * v
	end
	return sum
end
print(factorial(5))
function addFactorial()
	local sum
	sum = 0
	for i = 1, 20 do
		sum = sum + factorial(i)
	end
	return sum
end
print(addFactorial())



