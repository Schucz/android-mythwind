

reserved = {
	["while"] = true, ["end"] = true,
	["function"] = true, ["local"] = true,
}

-------�� bag -------
function insert(bag, element)
	bag[element] = (bag[element] or 0) + 1
end
function remove(bag, element)
	local count = bag[element]
	bag[element] = (count and count > 1) and count - 1 or nil
end


---========= �ַ�������------
-- ��ȡ�ļ��Ĵ��룬��Լ�����ļ������ܿ�������
local buff = ""
for line in io.lines() do
	buff = buff .. line .. "\n"
end
-- ���µ�ʵ��ȡ�������ʵ�֣����Լ�С����
local t = { }
for line in io.lines() do
	t[#t + 1] = line .. "\n"
end
local s = table.concat(t)
---========= ע�⣺��ȡ�ļ�������io,read��*allʵ��



