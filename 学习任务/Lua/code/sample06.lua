
---------  ���뺯��  ---------

--[[
a = { p = print }
a.p("Hello World")  -->Hello World
print = math.sin
a.p(print(1))  -->0.8414709848079
sin = a.p
sin(10, 20) --> 10	20


function foo(x) return 2 * x end
foo = function(x)  return 2 * x end


network = {
	{name = "first", ip = "210.26.30.34"},
	{name = "second", ip = "210.26.30.23"},
	{name = "third", ip = "210.26.30.12"},
	{name = "fouth", ip = "210.26.30.20"},
}
--- ��name�ֶΡ���������ַ�˳��������
table.sort(network, function(a, b) return (a.name > b.name) end)
for i, v in ipairs(network) do
print(v.name, v.ip)
end

-- �߽׺���������
function derivative(f, delta)
	delta = delta or 1e-4
	return function(x)
		return (f(x + delta) - f(x)) / delta
		end
end
c = derivative(math.sin)
print(math.cos(10), c(10))  --> -0.83907152907645	-0.83904432662041

--]]

--------- �պϺ��� --------
names = { "Peter", "Paul", "Mary" }
grades = { Mary = 10, Paul = 7, Peter = 8 }
function sortbygrade(names, grades)
	table.sort(names, function(n1, n2)
		return grades[n1] > grades[n2]  -- �Ƚ��꼶
	end)
end

Lib = {}
Lib.foo = function(x, y) return x + y end
Lib.goo = function(x, y) return x - y end

---------  ����β����  ------
-- �Թ���Ϸ
function room1()
	local move = io.read()
	if move == "south" then return room3()
	elseif move == "east" then return room2()
	else print("invalid move")
	return room1()
	end
end

function room2()
	local move = io.read()
	if move == "south" then return room4()
	elseif move == "west" then return room1()
	else print("invalid move")
	return room2()
	end
end

function room3()
	local move = io.read()
	if move == "north" then return room1()
	elseif move == "east" then return room4()
	else print("invalid move")
	return room3()
	end
end

function room4()
	print("congratulations!")
end

room1();

