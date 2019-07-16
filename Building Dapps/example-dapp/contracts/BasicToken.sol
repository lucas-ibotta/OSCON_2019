pragma solidity ^0.4.24;

contract Basictoken {
  address public issuer;
  mapping (address => uint256) private _allocationTable;

  constructor() public {
    issuer = msg.sender;
    _allocationTable[msg.sender] = 100; // give issuer balance of 100
  }
}