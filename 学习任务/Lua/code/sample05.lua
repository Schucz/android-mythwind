
-------- ���� ---------

--[[

print(8 * 9, 9 / 8)
print(os.date())  -- ��ȡϵͳʱ��

print("hello world")
print "hello world"

dofile ('hello.lua')
dofile 'hello.lua'



function add(a)
local sum = 0
	for i, v in ipairs(a) do
sum = sum + v
end
return sum
end



function f(a, b)
return a or b
end

print(f(3))
print(f(3, 4))
print(f(nil, 4))


--------- �෵��ֵ����  -------
function maximum(a)
local mi = 1  --��������ֵ
local m = a[mi]  --���ֵ
for i, val in ipairs(a) do
	if val > m then
	mi = i
	m = val
	end
end
	return m, mi
end

print(maximum({8, 10, 23, 12, 5}))


function foo0() end
function foo1() return "a" end
function foo2() return "a", "b" end
print(foo0())
print(foo1())
print(foo2())
print(foo2(), 1)
print(foo2() .. "X")
function foo(i)
	if i == 0 then return foo0()
	elseif i == 1 then return foo1()
	elseif i == 2 then return foo2()
	end
end
print(foo(1))
print(foo(2))
print(foo(0))

--- unpack �����������Ե�Ԫ��
print(unpack{10, 20, 30})
a,b = unpack{10, 20, 30}
print(a,b)


------- �䳤���� --------
function add(...)
	local s = 0;
	for i, v in ipairs{...} do
	s = s + v
	end
	return s
end
print(add(3, 4, 10, 25, 12))  --> 54


------- �ı���ʽ������� --------
function fwrite(fmt, ...)
return io.write(string.format(fmt, ...))
end
--fwrite()
fwrite("a")
fwrite("%d%d", 4, 5)

--- select('#', ...) �������б䳤����������
for i = 1, select('#', ...) do
local arg = select(i, ...)  --�õ���i������
<ѭ����>
end

--]]



