
--[[
���дһ���򵥵�ͳ�ƿ�(Statist).ʵ�ֶ�һ�������������������ͳ�ƹ���:
a) ƽ������Avg��
b) ����(Count)
c) ���(Sum)
d) ���ֵ(Max)
e) ��Сֵ(Min)
f) ����(Varp)
g) ��׼��(StdDevP) ��ʾ��
��׼���Ƿ������Ľ��(�����������ƽ����) 
�����������ݵ�ƽ��ֵ��m ���ʽs^2=1/n[(x1-m)^2+(x2-m)^2+...+(xn-m)^2]
--]]

arr = { 10, 20, 15, 40, 25, 35, 30 }
--c ���
function arrSum(arr)
	local sum = 0
	for i, v in pairs(arr) do
		sum = sum + v
	end
	return sum
end

-- ����
function arrCount(arr)
	local count = 0
	for i, v in pairs(arr) do
		count = count + 1
	end
	return count
end

-- ��ƽ����
function arrAvg(arr)
	return arrSum(arr) / arrCount(arr)
end

-- ���ֵ
function arrMax(arr)
	local mi = 1  --��������ֵ
	local m = arr[mi]  --���ֵ
	for i, v in pairs(arr) do
		if v > m then
		mi = i
		m = v
		end
	end
	return m, mi
end

-- ��Сֵ
function arrMin(arr)
	local mi = 1  --��С������ֵ
	local m = arr[mi]  --��Сֵ
	for i, v in pairs(arr) do
		if v < m then
		mi = i
		m = v
		end
	end
	return m, mi
end

-- ���� Varp �����������ݵ�ƽ��ֵ��m ���ʽs^2=1/n[(x1-m)^2+(x2-m)^2+...+(xn-m)^2]
function arrVarp(arr)
	local m
	local n
	local temp = 0
	m = arrAvg(arr)
	n = arrCount(arr)
	for i, v in pairs(arr) do
		temp = temp + (v - m) ^ 2
	end
	return temp / n
end

-- ��׼�� StdDevP
function arrStdDevP(arr)
	local varp
	local v
	varp = arrVarp(arr)
	for var = 1, varp do
		if var * var == varp then
			v = var
		break
		end
	end
	return v
end

print("���� { 10, 20, 15, 40, 25, 35, 30 } �ĺ� sum = " .. arrSum(arr))
print("���� { 10, 20, 15, 40, 25, 35, 30 } �ĳ��� count = " .. arrCount(arr))
print("���� { 10, 20, 15, 40, 25, 35, 30 } ��ƽ���� avg = " .. arrAvg(arr))
a, b = arrMax(arr)
print("���� { 10, 20, 15, 40, 25, 35, 30 } �����ֵ max = " .. a .. ", ����ֵ maxIndex = " .. b)
a, b = arrMin(arr)
print("���� { 10, 20, 15, 40, 25, 35, 30 } ����Сֵ min = " .. a .. ", ����ֵ minIndex = " .. b)
print("���� { 10, 20, 15, 40, 25, 35, 30 } �ķ��� varp = " .. arrVarp(arr))
print("���� { 10, 20, 15, 40, 25, 35, 30 } �ı�׼�� StdDevP = " .. arrStdDevP(arr))


