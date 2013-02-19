
--[[
---- һ��Эͬ������4�ֲ�ͬ��״̬��suspended��running��normal��dead----
co = coroutine.create(function() print("hello") end)
print(co)
print("Status:" .. coroutine.status(co))
coroutine.resume(co)
print("Status:" .. coroutine.status(co))

print()
function f()
	for i = 1,  10 do
		print(i)
		coroutine.yield()
	end
end
co = coroutine.create(f)
coroutine.resume(co) -->1
coroutine.resume(co) -->2
coroutine.resume(co) -->3


co = coroutine.create(function() print("hello") end)
coroutine.resume(co) -->hello
print(coroutine.status(co)) -->dead
print(coroutine.resume(co)) -->alse	cannot resume dead coroutine

--]]

co = coroutine.create(function() 
			for i = 1, 10 do
				print("co", i)
				coroutine.yield()
			end
		 end)
coroutine.resume(co)  --> co	1
print(coroutine.status(co))  --> suspended
-- suspended �����Ļ��������yield������
--Эͬ����A����Эͬ����B��A��������״̬����Ϊ����̬

co = coroutine.create(function(a, b, c) 
			print("co", a, b, c)
		end)
coroutine.resume(co, 1, 2, 3)

co = coroutine.create(function(a, b) 
			coroutine.yield(a + b, a - b)
		end)
print(coroutine.resume(co, 25, 10))  --> true	35	15


--[[ 
	������/����������
--]]
-- ������
function producer()
	return coroutine.create(
		function()
			while true do
				local x = io.read() -- �����µ�ֵ
				send(x)  --���͸�������
			end
		end)
end
-- ������
function consumer(prod)
	while true do
		local x = receive(prod) -- �������߽���ֵ
		io.write(x, "\n")  --�����µ�ֵ
	end
end
-- �ܵ�
function filter(prod)
	return coroutine.create(function()
		for line = 1, math.huge do
			local x = receive(prod)  --��ȡ��ֵ
			x = string.format("%5d%s", line, x)
			send(x)  -- ����ֵ���͸�������
		end
	end)
end
--����
function send(x)
	coroutine.yield(x)
end
function receive(prod)
	local status, value = coroutine.resume(prod)
	return value
end

consumer(filter(producer()))

